package com.example.gourmetsearchercompose.ui

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

/**
 * アプリケーションのルートコンポーネント
 * ナビゲーションホストとアプリバーを配置する
 * @param modifier Modifier
 * @param navController ナビゲーションコントローラー
 */
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

/** アプリケーションの画面定義 */
enum class AppScreens(val title: Int) {
    InputKeyword(title = R.string.input_keyword),
    SearchLocation(title = R.string.search_location),
    RestaurantList(title = R.string.restaurant_list),
    RestaurantDetail(title = R.string.restaurant_detail),
}
