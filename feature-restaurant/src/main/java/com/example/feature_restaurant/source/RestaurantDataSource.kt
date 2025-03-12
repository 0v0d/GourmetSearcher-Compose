package com.example.feature_restaurant.source

import com.example.core.api.model.client.RestaurantList
import com.example.feature_restaurant.model.SearchTerms
import retrofit2.Response

interface RestaurantDataSource {
    suspend fun getRestaurants(searchTerms: SearchTerms): Response<RestaurantList>?
}