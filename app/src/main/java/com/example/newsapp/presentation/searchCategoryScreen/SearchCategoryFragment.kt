package com.example.newsapp.presentation.searchCategoryScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSearchCategoryBinding
import com.example.newsapp.presentation.common.NewsViewModel

class SearchCategoryFragment : Fragment(), SearchViewModelListener {

    private lateinit var binding: FragmentSearchCategoryBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var searchCategoryViewModel: SearchCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_category, container, false)
        searchCategoryViewModel =
            ViewModelProvider(requireActivity()).get(SearchCategoryViewModel::class.java)
        newsViewModel =
            ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        searchCategoryViewModel.setOnSearchViewModelListener(this)
        binding.viewModel = searchCategoryViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onSearchBtbClick(searchQuery: String) {
        newsViewModel.searchQuery = searchQuery
        findNavController().navigate(
            R.id.action_searchCategoryFragment_to_headlinesFragment
        )
    }

}