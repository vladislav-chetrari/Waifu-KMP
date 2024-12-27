package org.vchetrari.waifu.feature.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.vchetrari.waifu.feature.list.data.WaifuRepository
import org.vchetrari.waifu.feature.list.data.di.DataDIModel
import org.vchetrari.waifu.feature.list.data.model.domain.Tag

class WaifuListByTagViewModel(
    private val repository: WaifuRepository = DataDIModel.waifuRepository,
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(WaifuListByTagState())
    val stateFlow: StateFlow<WaifuListByTagState> = mutableStateFlow.asStateFlow()

    private val filterTagsComparator = compareBy<WaifuListByTagState.FilterTag>({ !it.isSelected }, { it.tag.isNSFW }, { it.tag.name })

    init {
        viewModelScope.launch(Dispatchers.Default) {
            mutableStateFlow.update { it.copy(isLoading = true) }
            val filterTags = repository.tags()
                .map { tags -> tags.map { WaifuListByTagState.FilterTag(false, it) } }
                .getOrDefault(emptyList())
                .sortedWith(filterTagsComparator)
            mutableStateFlow.update { it.copy(isLoading = false, filterTags = filterTags) }
            reloadList()
        }
    }

    fun onTagToggle(tag: Tag) {
        viewModelScope.launch(Dispatchers.Default) {
            val newFilterTags = stateFlow.value.filterTags
                .map { if (it.tag == tag) it.copy(isSelected = !it.isSelected) else it }
                .sortedWith(filterTagsComparator)
            mutableStateFlow.update { it.copy(filterTags = newFilterTags) }
            reloadList()
        }
    }

    private suspend fun reloadList() {
        mutableStateFlow.update { it.copy(isLoading = true) }
        val tags = mutableStateFlow.value.filterTags
            .filter(WaifuListByTagState.FilterTag::isSelected)
            .map { it.tag.name }
        val waifuList = repository.search(tags).getOrDefault(emptyList())
        mutableStateFlow.update { it.copy(isLoading = false, waifuList = waifuList) }
    }
}