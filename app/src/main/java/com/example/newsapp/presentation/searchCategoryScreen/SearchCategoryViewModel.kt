package com.example.newsapp.presentation.searchCategoryScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchCategoryViewModel : ViewModel() {
    var searchQuery: MutableLiveData<String> = MutableLiveData("")

    private var listener: SearchViewModelListener? = null

    private val _state: MutableLiveData<CategorySearchState> =
        MutableLiveData(CategorySearchState())
    val state: LiveData<CategorySearchState> = _state

    fun onSearchQueryChange(s: CharSequence, start: Int, before: Int, count: Int) {
        _state.value = CategorySearchState()
        searchQuery.value = s.toString()
    }

    fun setOnSearchViewModelListener(listener: SearchViewModelListener) {
        this.listener = listener
    }

    fun searchBtnClick() {
        if (searchQuery.value.isNullOrEmpty()) {
            _state.value = _state.value?.copy(error = true)
            return
        } else {
            listener?.onSearchBtbClick(searchQuery.value.toString())
        }
    }

}

interface SearchViewModelListener {
    fun onSearchBtbClick(searchQuery: String)
}

data class CategorySearchState(
    val error: Boolean = false
)