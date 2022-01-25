package com.example.newsapp.presentation.searchCategoryScreen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSearchCategoryBinding
import com.example.newsapp.util.viewModelCreator

class SearchCategoryFragment : Fragment(R.layout.fragment_search_category),
    SearchViewModelListener {

    private lateinit var binding: FragmentSearchCategoryBinding

    private val searchCategoryViewModel by viewModelCreator {
        SearchCategoryViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchCategoryBinding.bind(view)
        searchCategoryViewModel.setOnSearchViewModelListener(this)
        binding.viewModel = searchCategoryViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onSearchBtbClick(searchQuery: String) {
        findNavController().navigate(
            R.id.action_searchCategoryFragment_to_mainFragment,
            bundleOf("searchQuery" to searchQuery)
        )
    }

}