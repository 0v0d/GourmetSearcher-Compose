package com.example.feature_keyword.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.core.utils.PreviewComponentWrapper
import com.example.feature_keyword.R

/**
 * カスタムテキストフィールド
 * @param focusRequester フォーカスリクエスター
 * @param textFieldValue テキストフィールド値
 * @param onValueChange 値変更時のコールバック
 * @param showClearButton クリアボタン表示フラグ
 * @param onClearClick クリアボタンクリック時のコールバック
 * @param modifier Modifier
 */
@Composable
fun CustomTextField(
    focusRequester: FocusRequester,
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    showClearButton: Boolean,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textFieldValue,
        onValueChange = onValueChange,
        modifier = modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .testTag("searchTextField"),
        colors = TextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        trailingIcon = {
            if (showClearButton) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        },
        textStyle = TextStyle(fontSize = 18.sp),
        label = {
            Text(
                text = stringResource(R.string.search_input_hint),
                color = Color.Gray
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.None)
    )
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun CustomTextFieldPreview() {
    PreviewComponentWrapper {
        CustomTextField(
            focusRequester = FocusRequester(),
            textFieldValue = TextFieldValue(),
            onValueChange = {},
            showClearButton = false,
            onClearClick = {}
        )
    }
}
