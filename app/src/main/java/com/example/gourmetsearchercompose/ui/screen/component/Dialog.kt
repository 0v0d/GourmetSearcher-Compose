package com.example.gourmetsearchercompose.ui.screen.component

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.theme.Blue

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
                textColor = Blue
            )
        },
        // OKボタン以外ないので何もしない
        onDismissRequest = { },
        dismissButton = null
    )
}
