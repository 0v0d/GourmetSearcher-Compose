package com.example.gourmetsearchercompose.ui.screen.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * アプリバーのコンテンツ
 * @param onClick 戻るボタンクリック時のコールバック
 * @param title タイトル
 * @param modifier Modifier
 */
@Composable
fun AppBarContent(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        },
    )
}
