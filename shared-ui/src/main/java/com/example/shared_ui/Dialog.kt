package com.example.shared_ui

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

/**
 * ユーザーに対して許可が必要な旨のダイアログ
 * @param onConfirmClick OKボタンクリック時のコールバック
 * @param errorMessage エラーメッセージ
 */
@Composable
fun Dialog(
    onConfirmClick: () -> Unit,
    @StringRes errorMessage: Int
) {
    AlertDialog(
        text = {
            Text(
                text = stringResource(
                    id = errorMessage
                ),
                fontSize = 13.sp
            )
        },
        confirmButton = {
            CustomOutlinedButton(
                text = R.string.common_ok,
                onClick = onConfirmClick,
                textColor = MaterialTheme.colorScheme.primary
            )
        },
        // OKボタン以外ないので何もしない
        onDismissRequest = { },
        dismissButton = null
    )
}
