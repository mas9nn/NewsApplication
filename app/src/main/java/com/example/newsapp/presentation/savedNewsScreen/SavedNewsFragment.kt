package com.example.newsapp.presentation.savedNewsScreen

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
import com.example.newsapp.presentation.common.NewsAdapter
import com.example.newsapp.presentation.common.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding

    private lateinit var newsViewModel: NewsViewModel

    private var adapter = NewsAdapter()


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
        //Init recyclerView
        binding.newsRecycler.let {
            val layoutManager = LinearLayoutManager(requireContext())
            it.layoutManager = layoutManager
            it.adapter = adapter
            adapter.addOnItemClickListener(object : HeadlinesItemClickListener {
                override fun onClick(article: Article) {
                    val bundle = bundleOf("article" to article)
                    findNavController().navigate(
                        R.id.action_savedNewsFragment_to_detailsFragment,
                        bundle
                    )
                }

                override fun onBookmarkClick(article: Article, index: Int) {
                    adapter.updateNews(index)
                    newsViewModel.deleteArticleFromDb(article)
                    adapter.deleteItem(index)
                }
            })
        }
        //Observe request from server
        newsViewModel.state.observe(viewLifecycleOwner, {
            it?.let { state ->
                if (state.result != null) {
                    adapter.addData(state.result.articles)
                }
            }
        })

        newsViewModel.getSavedNews()
    }
}