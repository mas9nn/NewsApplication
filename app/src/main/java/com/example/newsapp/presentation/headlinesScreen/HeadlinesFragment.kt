package com.example.newsapp.presentation.headlinesScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.common.HeadlinesItemClickListener
import com.example.newsapp.presentation.common.NewsViewModel
import com.example.newsapp.presentation.common.NewsViewModelFactory
import com.example.newsapp.presentation.common.NewsAdapter
import com.example.newsapp.util.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HeadlinesFragment : Fragment() {


    private lateinit var binding: FragmentNewsListBinding

    private lateinit var newsViewModel: NewsViewModel

    private var adapter = NewsAdapter()

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var count = 1
    private var maxCount = Integer.MAX_VALUE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false)
        newsViewModel =
            ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        binding.viewModel = newsViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Get data when user swipe to refresh
        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = true
            isLastPage = false
            count = 1
            adapter.clearData()
            newsViewModel.getHeadlines(count)
        }
        //Init recyclerView
        binding.newsRecycler.let {
            val layoutManager = LinearLayoutManager(requireContext())
            it.layoutManager = layoutManager
            it.adapter = adapter
            //Add pagination to recyclerView
            it.addOnScrollListener(object :
                PaginationScrollListener(layoutManager) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    if (count <= maxCount) {
                        newsViewModel.getHeadlines(count)
                        isLoading = true
                    }
                }
            })
            adapter.addOnItemClickListener(object : HeadlinesItemClickListener {
                override fun onClick(article: Article) {
                    val bundle = bundleOf("article" to article)
                    findNavController().navigate(
                        R.id.action_headlinesFragment_to_detailsFragment,
                        bundle
                    )
                }
            })
        }
        //Observe request from server
        newsViewModel.state.observe(viewLifecycleOwner, {
            it?.let { state ->
                if (state.result != null) {
                    adapter.addData(state.result.articles)
                    count++
                } else if (!state.loading) {
                    isLastPage = true
                }
                isLoading = false
                binding.swipeToRefresh.isRefreshing = false
            }
        })

        newsViewModel.getHeadlines(count)
    }
}