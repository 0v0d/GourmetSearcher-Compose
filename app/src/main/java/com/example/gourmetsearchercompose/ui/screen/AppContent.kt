package com.example.gourmetsearchercompose.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.navigation.NavigationGraph
import com.example.gourmetsearchercompose.ui.screen.common.AppBar

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: AppScreens.InputKeyword.name

    val currentScreen = when {
        currentRoute.startsWith(AppScreens.SearchLocation.name) -> AppScreens.SearchLocation
        currentRoute.startsWith(AppScreens.RestaurantList.name) -> AppScreens.RestaurantList
        currentRoute.startsWith(AppScreens.RestaurantDetail.name) -> AppScreens.RestaurantDetail
        else -> AppScreens.InputKeyword
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

/**
 * アプリケーションの画面
 * @param title タイトル
 */
enum class AppScreens(val title: Int) {
    InputKeyword(title = R.string.input_keyword_top_bar_title),
    SearchLocation(title = R.string.search_location_top_bar_title),
    RestaurantList(title = R.string.restaurant_list_search_result_top_bar_title),
    RestaurantDetail(title = R.string.restaurant_detail_top_bar_title),
}