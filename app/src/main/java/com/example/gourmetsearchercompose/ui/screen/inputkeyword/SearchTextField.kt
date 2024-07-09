package com.example.gourmetsearchercompose.ui.screen.inputkeyword

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.theme.Blue

/**
 * 検索テキストフィールド
 * @param query 検索クエリ
 * @param onQueryChange 検索クエリ変更時のコールバック
 * @param modifier Modifier
 */
@Composable
fun SearchTextField(
    focusRequester: FocusRequester,
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showClearButton by remember { mutableStateOf(false) }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = query
            )
        )
    }

    LaunchedEffect(query) {
        if (query != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = query,
                selection = TextRange(query.length)
            )
        }
    }

    LaunchedEffect(Unit) { focusRequester.requestFocus() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchIcon()
        CustomTextField(
            focusRequester = focusRequester,
            textFieldValue = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onQueryChange(it.text)
                showClearButton = it.text.isNotBlank()
            },
            showClearButton = showClearButton,
            onClearClick = { onQueryChange("") }
        )
    }
}

/**
 * 検索アイコン
 * @param modifier Modifier
 */
@Composable
private fun SearchIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "search",
        modifier = modifier.padding(start = 4.dp),
    )
}

/**
 * カスタムテキストフィールド
 * @param textFieldValue TextFieldValue
 * @param onValueChange テキスト変更時のコールバック
 * @param showClearButton クリアボタン表示フラグ
 * @param onClearClick クリアボタンクリック時のコールバック
 * @param modifier Modifier
 */
@Composable
private fun CustomTextField(
    focusRequester: FocusRequester,
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    showClearButton: Boolean,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = textFieldValue,
        onValueChange = onValueChange,
        modifier = modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .testTag("textField"),
        colors = TextFieldDefaults.colors(
            cursorColor = Blue,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        trailingIcon = {
            if (showClearButton) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "clear",
                    )
                }
            }
        },
        textStyle = TextStyle(fontSize = 18.sp),
        label = {
            Text(
                text = stringResource(R.string.input_keyword_search_input_hint),
                color = Color.Gray,
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.None),
    )
}
