package org.vchetrari.waifu.feature.list.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI
import org.vchetrari.waifu.feature.list.data.model.domain.Tag
import org.vchetrari.waifu.feature.list.data.model.domain.Waifu
import org.vchetrari.waifu.feature.list.featureWaifuModule

@Composable
fun WaifuListByTagScreen() = withDI(featureWaifuModule) {
    val viewModel by rememberInstance<WaifuListByTagViewModel>()
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
    var tagsButtonWidthPx by remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            TagsButton(
                modifier = Modifier.onSizeChanged { tagsButtonWidthPx = it.width },
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
                    contentPadding = PaddingValues(
                        end = with(LocalDensity.current) { tagsButtonWidthPx.toDp() },
                    ),
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
    modifier: Modifier = Modifier,
    isActionOpen: Boolean,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = if (isActionOpen) 4.dp else 0.dp,
        ),
        containerColor =
            if (isActionOpen) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.secondaryContainer,
        onClick = onClick
    ) {
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterTagsCard(
    modifier: Modifier = Modifier,
    elevation: Dp,
    contentPadding: PaddingValues,
    filterTags: List<WaifuListByTagState.FilterTag>,
    onTagClick: (Tag) -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = elevation),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            filterTags.forEach { filterTag ->
                FilterChip(
                    selected = filterTag.isSelected,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = when {
                            filterTag.isSelected -> Color.Transparent
                            filterTag.tag.isNSFW -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.secondary
                        }
                    ),
                    leadingIcon = {
                        if (filterTag.isSelected) Icon(
                            modifier = Modifier.size(FilterChipDefaults.IconSize),
                            imageVector = Icons.Outlined.Done,
                            contentDescription = null,
                        )
                    },
                    label = { Text(filterTag.tag.name) },
                    onClick = { onTagClick(filterTag.tag) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                        selectedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
                        selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                    ),
                )
            }
        }
    }
}
