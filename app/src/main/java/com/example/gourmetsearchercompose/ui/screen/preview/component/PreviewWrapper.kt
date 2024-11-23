package com.example.gourmetsearchercompose.ui.screen.preview.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gourmetsearchercompose.theme.AppTheme

/**
 * プレビュー用ラッパー
 * @param modifier Modifier
 * @param darkTheme ダークモードかどうか
 * @param content プレビュー内容
 */
@Composable
fun PreviewWrapper(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    AppTheme(darkTheme = darkTheme) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

/**
 * プレビューコンポーネント用ラッパー
 * @param modifier Modifier
 * @param darkTheme ダークモードかどうか
 * @param content プレビュー内容
 */
@Composable
fun PreviewComponentWrapper(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    AppTheme(darkTheme = darkTheme) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}
