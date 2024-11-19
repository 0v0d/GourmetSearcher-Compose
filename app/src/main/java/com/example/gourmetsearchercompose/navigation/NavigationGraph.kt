package com.example.gourmetsearchercompose.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gourmetsearchercompose.model.data.decodeSearchTerms
import com.example.gourmetsearchercompose.model.data.encodeSearchTerms
import com.example.gourmetsearchercompose.model.domain.decodeRestaurantData
import com.example.gourmetsearchercompose.model.domain.encodeRestaurantData
import com.example.gourmetsearchercompose.ui.screen.AppScreens
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.InputKeyWordScreen
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.RestaurantDetailScreen
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.RestaurantListScreen
import com.example.gourmetsearchercompose.ui.screen.seachlocation.SearchLocationScreen

/**
 * ナビゲーショングラフ
 * @param navController ナビゲーションコントローラー
 */
@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.InputKeyword.name,
        modifier = modifier
    ) {
        addInputKeywordScreen(navController)
        addSearchLocationScreen(navController)
        addRestaurantListScreen(navController)
        addRestaurantDetailScreen()
    }
}

/**
 * キーワード入力画面の追加
 * @param navController ナビゲーションコントローラー
 */
private fun NavGraphBuilder.addInputKeywordScreen(navController: NavHostController) {
    composable(AppScreens.InputKeyword.name) {
        InputKeyWordScreen(
            onNavigateToSearchLocation = { inputText, range ->
                val safeName = Uri.encode(inputText)
                navController.navigateSingleTopTo(
                    "${AppScreens.SearchLocation.name}/$safeName,$range"
                )
            }
        )
    }
}

/**
 * 現在位置取得画面の追加
 * @param navController ナビゲーションコントローラー
 */
private fun NavGraphBuilder.addSearchLocationScreen(navController: NavHostController) {
    composable(
        route = "${AppScreens.SearchLocation.name}/{inputText},{range}",
        arguments = listOf(
            navArgument("inputText") { type = NavType.StringType },
            navArgument("range") { type = NavType.IntType }
        )
    ) { backStackEntry ->
        SearchLocationScreen(
            inputText = backStackEntry.arguments?.getString("inputText") ?: "",
            range = backStackEntry.arguments?.getInt("range") ?: 0,
            onNavigateToResultList = { searchTerms ->
                val encodedSearchTerms = encodeSearchTerms(searchTerms)
                navController.navigateSingleTopTo(
                    "${AppScreens.RestaurantList.name}/$encodedSearchTerms"
                )

                // 現在の画面をスタックから削除
                navController.popBackStack(
                    route = "${AppScreens.SearchLocation.name}/{inputText},{range}",
                    inclusive = true
                )
            }
        )
    }
}

/**
 * レストラン一覧画面の追加
 * @param navController ナビゲーションコントローラー
 */
private fun NavGraphBuilder.addRestaurantListScreen(navController: NavHostController) {
    composable(
        route = "${AppScreens.RestaurantList.name}/{searchTerms}",
        arguments = listOf(navArgument("searchTerms") { type = NavType.StringType })
    ) { backStackEntry ->
        val encodedSearchTerms = backStackEntry.arguments?.getString("searchTerms") ?: ""
        val searchTerms = decodeSearchTerms(encodedSearchTerms)
        RestaurantListScreen(
            searchTerms = searchTerms,
            onNavigateToDetail = { shopsDomain ->
                val encodedRestaurantData = encodeRestaurantData(shopsDomain)
                navController.navigateSingleTopTo(
                    "${AppScreens.RestaurantDetail.name}/$encodedRestaurantData"
                )
            },
            popBack = { navController.popBackStack() }
        )
    }
}

/**
 * レストラン詳細画面の追加
 */
private fun NavGraphBuilder.addRestaurantDetailScreen() {
    composable(
        route = "${AppScreens.RestaurantDetail.name}/{restaurantData}",
        arguments = listOf(
            navArgument("restaurantData") { type = NavType.StringType },
        )
    ) { backStackEntry ->
        val encodedRestaurantData = backStackEntry.arguments?.getString("restaurantData") ?: ""
        val restaurantData = decodeRestaurantData(encodedRestaurantData)
        RestaurantDetailScreen(
            restaurantData = restaurantData,
        )
    }
}

/**
 * シングルトップでナビゲーションする
 * @param route ルート
 */
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
