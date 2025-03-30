package org.vchetrari.waifu.feature.list.presentation

import androidx.compose.runtime.Immutable
import org.vchetrari.waifu.feature.list.data.model.domain.Tag
import org.vchetrari.waifu.feature.list.data.model.domain.Waifu

@Immutable
data class WaifuListByTagState(
    val isLoading: Boolean = false,
    val waifuList: List<Waifu> = emptyList(),
    val filterTags: List<FilterTag> = emptyList(),
) {

    data class FilterTag(
        val isSelected: Boolean = false,
        val tag: Tag,
    )
}