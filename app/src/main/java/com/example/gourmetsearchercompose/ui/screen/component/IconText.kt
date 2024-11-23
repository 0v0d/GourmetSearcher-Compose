package com.example.gourmetsearchercompose.ui.screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * アイコンテキスト
 * @param icon アイコン
 * @param text テキスト
 * @param modifier Modifier
 * @param iconSize アイコンサイズ
 */
@Composable
fun IconText(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    iconSize: Int = 18
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.wrapContentHeight()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colorScheme.onSecondaryContainer,
            modifier = Modifier
                .size(iconSize.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
