package com.example.newsapp.presentation.detailsScreen

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleDetailsBinding
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlin.math.abs


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_article_details, container, false)

        binding.collapsingToolbar.apply {
            setContentScrimColor(Color.WHITE)
        }

        binding.lifecycleOwner = this

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
        return binding.root
    }


}