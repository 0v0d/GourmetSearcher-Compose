package com.example.gourmetsearchercompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmetsearchercompose.model.api.RestaurantList
import com.example.gourmetsearchercompose.model.data.SearchTerms
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.model.domain.toDomain
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.usecase.network.GetRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**
 * レストラン検索画面のViewModel
 * @param getRestaurantUseCase ホットペッパーグルメAPIを利用して、レストラン情報を取得するUseCase
 */
@HiltViewModel
class RestaurantListViewModel
@Inject
constructor(
    private val getRestaurantUseCase: GetRestaurantUseCase,
) : ViewModel() {
    private val _shops = MutableStateFlow<ImmutableList<ShopsDomain>?>(null)

    /** レストラン情報 */
    val shops = _shops.asStateFlow()

    private val _searchState = MutableStateFlow(SearchState.LOADING)

    /** 検索状態 */
    val searchState = _searchState.asStateFlow()

    private lateinit var searchTerm: SearchTerms

    /**
     * レストラン検索
     * @param terms 検索条件
     */
    fun searchRestaurants(terms: SearchTerms) {
        _searchState.value = SearchState.LOADING
        viewModelScope.launch {
            try {
                searchTerm = terms
                handleResponse(getRestaurantUseCase(terms))
            } catch (e: Exception) {
                _searchState.value = SearchState.EMPTY_RESULT
            }
        }
    }

    /**
     * 検索結果が空でないかを返す
     * @return true: 検索結果が空でない, false: 検索結果が空
     */
    fun retrySearch() {
        if (searchTerm.keyword.isEmpty()) {
            return
        }
        searchRestaurants(searchTerm)
    }

    /**
     * レスポンスの処理
     * @param response HotPepperAPIからのレスポンス
     */
    private fun handleResponse(response: Response<RestaurantList>?) {
        if (response?.body() == null) {
            _searchState.value = SearchState.NETWORK_ERROR
            return
        }
        val repositories = response.body()?.results?.shops?.map { it.toDomain() }

        if (response.isSuccessful && !repositories.isNullOrEmpty()) {
            _searchState.value = SearchState.SUCCESS
            _shops.value = repositories.toImmutableList()
        } else {
            _searchState.value = SearchState.EMPTY_RESULT
        }
    }
}
