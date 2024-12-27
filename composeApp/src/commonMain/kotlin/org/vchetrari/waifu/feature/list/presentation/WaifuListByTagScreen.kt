package org.vchetrari.waifu.feature.list.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FilterChip
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import org.vchetrari.waifu.feature.list.data.model.domain.Tag
import org.vchetrari.waifu.feature.list.data.model.domain.Waifu

@Composable
fun WaifuListByTagScreen() {
    val viewModel = viewModel { WaifuListByTagViewModel() }
    val state by viewModel.stateFlow.collectAsState()
    WaifuListByTagScreen(
        state = state,
        onTagClick = viewModel::onTagToggle,
    )
}

@Composable
private fun WaifuListByTagScreen(
    state: WaifuListByTagState,
    onTagClick: (Tag) -> Unit,
) {
    var isTagsBottomSheetOpen by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            TagsButton(
                isActionOpen = !isTagsBottomSheetOpen,
                onClick = { isTagsBottomSheetOpen = !isTagsBottomSheetOpen },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(state.isLoading) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }
                WaifuList(modifier = Modifier.fillMaxSize(), list = state.waifuList)
            }
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomEnd),
                visible = isTagsBottomSheetOpen,
            ) {
                FilterTagsCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = 4.dp,
                    filterTags = state.filterTags,
                    onTagClick = onTagClick,
                )
            }
        }
    }
}

@Composable
private fun WaifuList(
    modifier: Modifier = Modifier,
    list: List<Waifu>,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        items(list) { waifu ->
            Waifu(
                modifier = Modifier.aspectRatio(3 / 4f),
                model = waifu,
            )
        }
    }
}

@Composable
private fun TagsButton(
    isActionOpen: Boolean,
    onClick: () -> Unit
) {
    FloatingActionButton(onClick = onClick) {
        val icon = if (isActionOpen) Icons.Outlined.Menu else Icons.Outlined.Close
        Icon(
            imageVector = icon,
            contentDescription = icon.name,
        )
    }
}

@Composable
private fun Waifu(
    modifier: Modifier = Modifier,
    model: Waifu,
) {
    AsyncImage(
        modifier = modifier,
        model = model.url,
        contentDescription = model.tags.joinToString { it.description },
        contentScale = ContentScale.Crop,
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
private fun FilterTagsCard(
    modifier: Modifier = Modifier,
    elevation: Dp,
    filterTags: List<WaifuListByTagState.FilterTag>,
    onTagClick: (Tag) -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = elevation,
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            filterTags.forEach { filterTag ->
                val accentColor = with(MaterialTheme.colors) { if (filterTag.tag.isNSFW) error else secondary }
                val contentOnAccentColor = with(MaterialTheme.colors) { if (filterTag.tag.isNSFW) onError else onSecondary }
                FilterChip(
                    selected = filterTag.isSelected,
                    onClick = { onTagClick(filterTag.tag) },
                    shape = RoundedCornerShape(8.dp),
                    selectedIcon = { Icons.Outlined.Done },
                    border = BorderStroke(1.dp, accentColor),
                    colors = ChipDefaults.outlinedFilterChipColors(
                        selectedBackgroundColor = accentColor,
                        selectedContentColor = contentOnAccentColor,
                        selectedLeadingIconColor = contentOnAccentColor,
                    )
                ) {
                    Text(filterTag.tag.name)
                }
            }
        }
    }
}
