package com.example.feature_restaurant.usecase

import com.example.feature_restaurant.SearchTerms
import com.example.feature_restaurant.repository.RestaurantRepository
import javax.inject.Inject

/**
 * ホットペッパーAPIからデータを取得するUseCase
 * @param restaurantRepository RestaurantRepository
 */
class GetRestaurantUseCase
@Inject
constructor(
    private val restaurantRepository: RestaurantRepository,
) {
    /**
     * ホットペッパーAPIからデータを取得する
     * @param searchTerms 検索条件
     * @return Response<RestaurantList>レストラン情報 or null
     */
    suspend operator fun invoke(searchTerms: SearchTerms) = restaurantRepository.searchRestaurantRepository(searchTerms)
}
