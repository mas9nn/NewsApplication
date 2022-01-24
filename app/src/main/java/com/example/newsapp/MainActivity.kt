package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.presentation.common.NewsViewModel
import com.example.newsapp.presentation.common.NewsViewModelFactory
import com.example.newsapp.util.hide
import com.example.newsapp.util.show
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory


    private lateinit var newsViewModel: NewsViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel =
            ViewModelProvider(this, factory).get(NewsViewModel::class.java)

        initNavigation()
        initToolbar()
    }

    private fun initNavigation() {
        val mainAppBar = findViewById<AppBarLayout>(R.id.mainAppBar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val newsNavHostFragment = findViewById<FragmentContainerView>(R.id.newsNavHostFragment)
        navController = newsNavHostFragment.findNavController()
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.searchCategoryFragment, R.id.detailsFragment,R.id.savedNewsFragment -> {
                    bottomNavigationView.hide()
                    mainAppBar.hide()
                }
                else -> {
                    bottomNavigationView.show()
                    mainAppBar.show()
                }
            }
        }
    }

    private fun initToolbar() {
        val mainToolbar = findViewById<Toolbar>(R.id.mainToolbar)
        mainToolbar.inflateMenu(R.menu.app_bar_menu)
        mainToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.searchCategoryScreen -> {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.news_nav_graph, true)
                        .setLaunchSingleTop(true)
                        .build()
                    navController.navigate(R.id.searchCategoryFragment, null, navOptions)
                    true
                }
                R.id.savedNewsScreen -> {
                    navController.navigate(R.id.savedNewsFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}