package com.example.newsapp.presentation.everythingScreen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentEverythingBinding
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.useCase.model.NewsUseCases
import com.example.newsapp.presentation.common.HeadlinesItemClickListener
import com.example.newsapp.presentation.common.NewsAdapter
import com.example.newsapp.util.PaginationScrollListener
import com.example.newsapp.util.viewModelCreator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EverythingFragment : Fragment(R.layout.fragment_everything) {
    @Inject
    lateinit var newsUseCase: NewsUseCases

    private lateinit var binding: FragmentEverythingBinding

    private val everythingViewModel by viewModelCreator {
        EverythingViewModel(newsUseCases = newsUseCase, getSearchQuery())
    }


    private var adapter = NewsAdapter()

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var page = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEverythingBinding.bind(view)

        binding.viewModel = everythingViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        //Get data when user swipe to refresh
        initSwipeToRefresh()
        //Init recyclerView
        initRecycler()
        //Observe request from server
        initObserver()
    }

    private fun initObserver() {
        everythingViewModel.state.observe(viewLifecycleOwner, {
            it?.let { state ->
                if (state.result != null) {
                    adapter.addData(state.result.articles)
                    page++
                    everythingViewModel.clearData()
                }
                isLastPage = state.isLastPage
                isLoading = state.loading
                binding.swipeToRefreshEverything.isRefreshing = page == 1
            }
        })
    }

    private fun initSwipeToRefresh() {
        binding.swipeToRefreshEverything.setOnRefreshListener {
            binding.swipeToRefreshEverything.isRefreshing = true
            isLastPage = false
            page = 1
            adapter.clearData()
            everythingViewModel.getEverything(page)
        }
    }

    private fun initRecycler() {
        binding.newsRecyclerEverything.let {
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
                        everythingViewModel.getEverything(page)
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
                        everythingViewModel.deleteArticleFromDb(article)
                    } else {
                        everythingViewModel.saveArticleToDb(article)
                    }
                    adapter.updateNews(index)
                }
            })
        }
    }

    private fun getSearchQuery() =
        findNavController().graph.arguments["searchQuery"]?.defaultValue?.toString() ?: ""
}