package com.example.newsapp.presentation.detailsScreen

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleDetailsBinding
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlin.math.abs


class DetailsFragment : Fragment(R.layout.fragment_article_details) {

    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleDetailsBinding.bind(view)

        binding.collapsingToolbar.apply {
            setContentScrimColor(Color.WHITE)
        }

        binding.lifecycleOwner = viewLifecycleOwner

        binding.article = arguments?.getParcelable("article")

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //Collapsed
                binding.back.setColorFilter(Color.BLACK)
            } else {
                //Expanded
                binding.back.setColorFilter(Color.WHITE)
            }
        })
    }

}