package com.example.gourmetsearchercompose.mock

import com.example.gourmetsearchercompose.model.api.BudgetData
import com.example.gourmetsearchercompose.model.api.GenreData
import com.example.gourmetsearchercompose.model.api.LargeAreaData
import com.example.gourmetsearchercompose.model.api.PCData
import com.example.gourmetsearchercompose.model.api.PhotoData
import com.example.gourmetsearchercompose.model.api.RestaurantList
import com.example.gourmetsearchercompose.model.api.Results
import com.example.gourmetsearchercompose.model.api.Shops
import com.example.gourmetsearchercompose.model.api.SmallAreaData
import com.example.gourmetsearchercompose.model.api.Urls
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.model.domain.toDomain
import kotlinx.collections.immutable.toImmutableList

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

    val sampleRestaurantList =
        sampleResponseData.results.shops.map { it.toDomain() }.toImmutableList()

    val sampleEmptyRestaurantList = emptyList<ShopsDomain>().toImmutableList()
}
