package com.example.newsapp.presentation.mainScreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.util.hide

import com.example.newsapp.util.show
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigation(view)
        initToolbar(view)
    }

    private fun initNavigation(view: View) {
        val mainAppBar = view.findViewById<AppBarLayout>(R.id.mainAppBar)

        navController =
            (childFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment)
                .navController

        val arg = NavArgument.Builder().setType(NavType.StringType)
            .setDefaultValue(this.arguments?.getString("searchQuery") ?: "").build()
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_graph_main)
        graph.addArgument("searchQuery", arg)
        navController.graph = graph

        val bottomNavigationView =
            view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.detailsFragment -> {
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

    private fun initToolbar(view: View) {
        val mainToolbar = view.findViewById<Toolbar>(R.id.mainToolbar)

        mainToolbar.inflateMenu(R.menu.app_bar_menu)

        mainToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.searchCategoryScreen -> {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.news_nav_graph, true)
                        .setLaunchSingleTop(true)
                        .build()
                    findNavController().navigate(R.id.searchCategoryFragment, null, navOptions)
                    true
                }
                R.id.savedNewsScreen -> {
                    findNavController().navigate(R.id.savedNewsFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

}