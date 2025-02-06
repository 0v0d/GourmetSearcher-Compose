package com.example.feature_restaurant.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.feature_restaurant.domain.ShopsDomain

/**
 * ページングソース
 * @param shops レストランリスト
 */
class ShopsPagingSource(
    private val shops: List<ShopsDomain>
) : PagingSource<Int, ShopsDomain>() {

    /**
     * ページングデータをロード
     * @param params ページングパラメータ
     * @return ページングデータ
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShopsDomain> {
        val position = params.key ?: 0
        val pageSize = params.loadSize
        val data = shops.drop(position).take(pageSize)
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 0) null else position - pageSize,
            nextKey = if (data.isEmpty()) null else position + pageSize
        )
    }

    /**
     * リフレッシュキーを取得
     * @param state ページング状態
     * @return リフレッシュキー
     */
    override fun getRefreshKey(state: PagingState<Int, ShopsDomain>): Int? {
        return state.anchorPosition
    }
}
