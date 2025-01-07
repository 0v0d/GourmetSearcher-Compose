package com.example.feature_restaurant.mock

import com.example.core.model.api.BudgetData
import com.example.core.model.api.GenreData
import com.example.core.model.api.LargeAreaData
import com.example.core.model.api.PCData
import com.example.core.model.api.PhotoData
import com.example.core.model.api.RestaurantList
import com.example.core.model.api.Results
import com.example.core.model.api.Shops
import com.example.core.model.api.SmallAreaData
import com.example.core.model.api.Urls
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.domain.toDomain
import com.example.feature_restaurant.mock.MockRestaurantData.sampleEmptyRestaurantList
import com.example.feature_restaurant.mock.MockRestaurantData.sampleResponseData
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList
import kotlinx.collections.immutable.toImmutableList
import retrofit2.Response

/**
 * モックレストランデータ
 * @property sampleResponseData モックレストランレスポンスデータ
 * @property sampleRestaurantList モックレストランリスト
 * @property sampleEmptyRestaurantList 空のモックレストランリスト
 */
object MockRestaurantData {
    val sampleResponseData =
        RestaurantList(
            Results(
                listOf(
                    Shops(
                        "モックレストランデータレストラン1",
                        "大阪府大阪市中央区難波１ - 7",
                        "なんば",
                        LargeAreaData("大阪"),
                        SmallAreaData("大阪難波"),
                        GenreData("居酒屋"),
                        BudgetData("2001～3000 円"),
                        "なんば駅徒歩1分",
                        Urls("http://www.testdata.jp/strJ003785192/?vos=nhppalsa000016"),
                        PhotoData(PCData(l = "http://test.jpg")),
                        "月～金",
                        "なし"
                    ),
                    Shops(
                        "モックレストランデータレストラン2",
                        "大阪府大阪市中央区難波１ - 7",
                        "なんば",
                        LargeAreaData("大阪"),
                        SmallAreaData("大阪難波"),
                        GenreData("居酒屋"),
                        BudgetData("2001～3000 円"),
                        "なんば駅徒歩1分",
                        Urls("http://www.testdata.jp/strJ003785192/?vos=nhppalsa000016"),
                        PhotoData(PCData(l = "http://test.jpg")),
                        "月～金",
                        "なし"
                    )
                ),
            ),
        )

    val sampleAPIResponse: Response<RestaurantList> = Response.success(
        sampleResponseData
    )

    val sampleRestaurantList =
        sampleResponseData.results.shops.map { it.toDomain() }.toImmutableList()

    val sampleEmptyRestaurantList = emptyList<ShopsDomain>().toImmutableList()
}
