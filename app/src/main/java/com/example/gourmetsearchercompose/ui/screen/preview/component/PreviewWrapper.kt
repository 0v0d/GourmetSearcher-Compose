package com.example.gourmetsearchercompose.ui.screen.preview.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gourmetsearchercompose.theme.AppTheme

@Composable
fun PreviewWrapper(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    AppTheme(darkTheme = darkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun PreviewComponentWrapper(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    AppTheme(darkTheme = darkTheme) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}