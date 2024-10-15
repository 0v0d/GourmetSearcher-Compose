package com.example.gourmetsearchercompose.ui.screen.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.theme.Blue

/**
 * エラーコンテンツ
 * @param errorMessage エラーメッセージ
 * @param onRetry リトライボタンクリック時のコールバック
 * @param onOpenSettings 設定画面遷移コールバック
 * @param modifier Modifier
 */
@Composable
fun ErrorContent(
    @StringRes errorMessage: Int,
    onRetry: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
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
            OutlinedButton(
                onClick = onRetry,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 30.dp)
            ) {
                Text(
                    text = stringResource(R.string.common_retry),
                    color = Blue
                )
            }
            OutlinedButton(
                onClick = onOpenSettings,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 30.dp)
            ) {
                Text(
                    text = stringResource(R.string.search_location_setting),
                    color = Blue
                )
            }
        }
    }
}
