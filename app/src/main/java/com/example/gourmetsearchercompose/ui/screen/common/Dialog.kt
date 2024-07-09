package com.example.gourmetsearchercompose.ui.screen.common

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.theme.Blue

/**
 * ユーザーに対して許可が必要な旨のダイアログ
 * @param onConfirmClick OKボタンクリック時のコールバック
 */
@Composable
fun Dialog(
    onConfirmClick: () -> Unit,
    @StringRes errorMessage: Int,
) {
    AlertDialog(
        // OKボタン以外ないので何もしない
        onDismissRequest = { },
        text = {
            Text(
                text = stringResource(
                    id = errorMessage
                ),
                fontSize = 13.sp,
            )
        },
        confirmButton = {
            OutlinedButton(
                onClick = onConfirmClick,
            ) {
                Text(
                    text = stringResource(
                        id = R.string.common_ok
                    ),
                    color = Blue
                )
            }
        },
        dismissButton = null,
    )
}
