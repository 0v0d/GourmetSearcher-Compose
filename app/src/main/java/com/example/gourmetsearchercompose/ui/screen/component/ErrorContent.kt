package com.example.gourmetsearchercompose.ui.screen.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R

/**
 * エラーコンテンツ
 * @param errorMessage エラーメッセージ
 * @param onRetry リトライボタンクリック時のコールバック
 * @param modifier Modifier
 * @param retryButtonText リトライボタンテキスト
 * @param onOpenSettings 設定画面を開くボタンクリック時のコールバック
 * @param isSettingButtonEnabled 設定画面を開くボタンが有効かどうか
 */
@Composable
fun ErrorContent(
    @StringRes errorMessage: Int,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes retryButtonText: Int = R.string.common_retry,
    onOpenSettings: () -> Unit = {},
    isSettingButtonEnabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = errorMessage),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            // リトライボタン
            CustomOutlinedButton(
                text = retryButtonText,
                onClick = onRetry
            )
            if (isSettingButtonEnabled) {
                // 設定画面を開くボタン
                CustomOutlinedButton(
                    text = R.string.search_location_setting,
                    onClick = onOpenSettings
                )
            }
        }
    }
}
