package com.example.feature_restaurant.state

/** 検索の状態を表すenum */
enum class SearchState {
    SUCCESS,
    LOADING,
    NETWORK_ERROR,
    EMPTY_RESULT,
}
