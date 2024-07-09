package com.example.gourmetsearchercompose.usecase.location

import com.example.gourmetsearchercompose.repository.SearchLocationRepository
import javax.inject.Inject

/**
 * 現在地を取得するUseCase
 * @param searchLocationRepository LocationRepository
 */
class GetCurrentLocationUseCase
@Inject
constructor(
    private val searchLocationRepository: SearchLocationRepository,
) {
    /**
     * 現在地を取得する
     * @return 位置情報 or null
     */
    suspend operator fun invoke() = searchLocationRepository.getLocation()
}
