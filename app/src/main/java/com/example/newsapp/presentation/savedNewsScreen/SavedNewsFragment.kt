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
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedNewsListBinding
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.useCase.model.NewsDbUseCases
import com.example.newsapp.presentation.common.HeadlinesItemClickListener
import com.example.newsapp.presentation.common.NewsAdapter
import com.example.newsapp.util.viewModelCreator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news_list) {

    @Inject
    lateinit var newsDbUseCases: NewsDbUseCases

    private val savedNewsViewModel by viewModelCreator {
        SavedNewsViewModel(newsDbUseCases)
    }

    private lateinit var binding: FragmentSavedNewsListBinding

    private var adapter = NewsAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsListBinding.bind(view)
        binding.viewModel = savedNewsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //Init recyclerView
        binding.newsRecycler.let {
            val layoutManager = LinearLayoutManager(requireContext())
            it.layoutManager = layoutManager
            it.adapter = adapter
            adapter.addOnItemClickListener(object : HeadlinesItemClickListener {
                override fun onClick(article: Article) {
                    val bundle = bundleOf("article" to article)
                    findNavController().navigate(
                        R.id.detailsFragment,
                        bundle
                    )
                }

                override fun onBookmarkClick(article: Article, index: Int) {
                    savedNewsViewModel.deleteArticleFromDb(article)
                    adapter.deleteItem(index)
                    showSnackbar()
                }
            })
        }
        //Observe request from server
        savedNewsViewModel.state.observe(viewLifecycleOwner, {
            it?.let { state ->
                state.articles?.let { list ->
                    adapter.setData(list)
                }
            }
        })
    }

    private fun showSnackbar() {
        Snackbar.make(binding.root, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
            setAction("Undo") {
                savedNewsViewModel.saveArticleToDb()
            }
            show()
        }
    }
}