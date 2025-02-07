package com.example.feature_restaurant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.api.model.client.RestaurantList
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.domain.toDomain
import com.example.feature_restaurant.model.SearchTerms
import com.example.feature_restaurant.source.ShopsPagingSource
import com.example.feature_restaurant.state.SearchState
import com.example.feature_restaurant.usecase.GetRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
    private val _shops = MutableStateFlow<Flow<PagingData<ShopsDomain>>?>(null)

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

            _shops.value = Pager(
                config = PagingConfig(
                    pageSize = 10,
                    prefetchDistance = 5,
                    initialLoadSize = 30,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { ShopsPagingSource(repositories) }
            ).flow.cachedIn(viewModelScope)
        } else {
            _searchState.value = SearchState.EMPTY_RESULT
        }
    }
}
