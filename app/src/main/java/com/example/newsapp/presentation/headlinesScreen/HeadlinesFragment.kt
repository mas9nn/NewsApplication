package com.example.newsapp.presentation.headlinesScreen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHeadlinesBinding
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.useCase.model.NewsUseCases
import com.example.newsapp.presentation.common.HeadlinesItemClickListener
import com.example.newsapp.presentation.common.NewsAdapter
import com.example.newsapp.util.PaginationScrollListener
import com.example.newsapp.util.viewModelCreator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeadlinesFragment : Fragment(R.layout.fragment_headlines) {
    @Inject
    lateinit var newsUseCase: NewsUseCases

    private lateinit var binding: FragmentHeadlinesBinding

    private val headlinesViewModel by viewModelCreator {
        HeadlinesViewModel(newsUseCases = newsUseCase, getSearchQuery())
    }

    private var adapter = NewsAdapter()

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var page = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHeadlinesBinding.bind(view)
        binding.viewModel = headlinesViewModel

        binding.lifecycleOwner = this
        //Get data when user swipe to refresh
        initSwipeToRefresh()
        //Init recyclerView
        initRecycler()
        //Init observer
        initObserver()
    }

    private fun initSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = true
            isLastPage = false
            page = 1
            adapter.clearData()
            headlinesViewModel.getHeadlines(1)
        }
    }

    private fun initRecycler() {
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
                    if (!isLoading && !isLastPage) {
                        headlinesViewModel.getHeadlines(page)
                        isLoading = true
                    }
                }
            })
            adapter.addOnItemClickListener(object : HeadlinesItemClickListener {
                override fun onClick(article: Article) {
                    val bundle = bundleOf("article" to article)
                    findNavController().navigate(
                        R.id.detailsFragment,
                        bundle
                    )
                }

                override fun onBookmarkClick(article: Article, index: Int) {
                    if (article.saved) {
                        headlinesViewModel.deleteArticleFromDb(article)
                    } else {
                        headlinesViewModel.saveArticleToDb(article)
                    }
                    adapter.updateNews(index)
                }
            })
        }
    }

    private fun initObserver() {
        headlinesViewModel.state.observe(viewLifecycleOwner, {
            it?.let { state ->
                if (state.result != null) {
                    adapter.addData(state.result.articles)
                    page++
                    headlinesViewModel.clearData()
                }
                isLastPage = state.isLastPage
                isLoading = state.loading
                binding.swipeToRefresh.isRefreshing = (page == 1) && state.noContent.isEmpty()
            }
        })
    }

    private fun getSearchQuery() =
        findNavController().graph.arguments["searchQuery"]?.defaultValue?.toString() ?: ""
}