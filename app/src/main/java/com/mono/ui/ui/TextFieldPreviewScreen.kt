package com.mono.ui.ui

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mono.ui.MonoBasicButton
import com.mono.ui.MonoIconButton
import com.mono.ui.MonoTextField
import com.mono.ui.designsystem.components.PreviewContainerRow
import com.mono.ui.designsystem.components.PreviewScaffold
import com.mono.ui.designsystem.icons.PreviewIcons
import kotlinx.serialization.Serializable

@Serializable
object TextFieldPreviewScreen

fun NavGraphBuilder.textFieldPreviewScreenRoute(
    navController: NavHostController
) {
    composable<TextFieldPreviewScreen> {
        TextFieldPreviewScreen(
            onNavigate = navController::navigateUp,
        )
    }
}

@Composable
fun TextFieldPreviewScreen(
    onNavigate: () -> Unit,
) {
    val searchState = rememberTextFieldState("")
    val renameState = rememberTextFieldState("")
    val emailState = rememberTextFieldState("")
    val numberState = rememberTextFieldState("")

    PreviewScaffold(
        modifier = Modifier.imePadding(),
        onNavigate = onNavigate,
    ) {
        PreviewContainerRow(
            title = "Mono TextFields"
        ) {
            MonoTextField(
                state = rememberTextFieldState("Basic Text Field"),
            )

            MonoTextField(
                state = emailState,
                placeholder = "Email",
                startIcon = PreviewIcons.Email,
            )

            MonoTextField(
                state = renameState,
                placeholder = "Rename",
                endIcon = PreviewIcons.Close.takeIf { renameState.text.isNotEmpty() },
                onEndIconClick = { renameState.clearText() }
            )

            MonoTextField(
                state = searchState,
                placeholder = "Search",
                startIcon = PreviewIcons.Search,
                endIcon = PreviewIcons.Close.takeIf { searchState.text.isNotEmpty() },
                onEndIconClick = {
                    searchState.clearText()
                }
            )

            MonoTextField(
                state = numberState,
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .height(IntrinsicSize.Min),
                contentPadding = PaddingValues(end = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                lineLimits = TextFieldLineLimits.SingleLine,
                horizontalSpace = 8.dp,
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text("Enter phone number")
                },
                startIcon = {
                    MonoBasicButton(
                        onClick = {},
                        contentColor = colorScheme.onSurfaceVariant,
                        color = colorScheme.surfaceContainerHighest,
                        shape = RectangleShape,
                        modifier = Modifier.fillMaxHeight(),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Text(text = "+92", style = MaterialTheme.typography.labelLarge)
                    }
                },
                endIcon = {
                    if (numberState.text.isNotEmpty()) {
                        MonoIconButton(
                            icon = PreviewIcons.Close,
                            onClick = { numberState.clearText() }
                        )
                    }
                },
            )
        }
    }
}