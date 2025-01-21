@file:OptIn(ExperimentalLayoutApi::class)

package com.mono.ui.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mono.ui.MonoAlertDialog
import com.mono.ui.MonoButton
import com.mono.ui.MonoIcon
import com.mono.ui.designsystem.components.PreviewContainerRow
import com.mono.ui.designsystem.components.PreviewScaffold
import com.mono.ui.designsystem.icons.PreviewIcons
import com.mono.ui.rememberDismissibleState
import kotlinx.serialization.Serializable

@Serializable
object DialogPreviewScreen

fun NavGraphBuilder.dialogPreviewScreenRoute(
    navController: NavHostController
) {
    composable<DialogPreviewScreen> {
        DialogPreviewScreen(
            onNavigate = navController::navigateUp
        )
    }
}

@Composable
fun DialogPreviewScreen(
    onNavigate: () -> Unit = {}
) {
    val basicDialog = rememberDismissibleState()
    val basicSingleButtonDialog = rememberDismissibleState()
    val fullDialog = rememberDismissibleState()
    val textDialog = rememberDismissibleState()
    val noButtonDialog = rememberDismissibleState()
    val onlyTextDialog = rememberDismissibleState()

    MonoAlertDialog(
        state = basicDialog,
        title = "Basic Dialog",
        text = "This is basic alert dialog with title, text and buttons. This does not include an icon.",
        primaryButton = "Ok",
        onPrimaryButtonClick = { basicDialog.dismiss() },
        secondaryButton = "Cancel",
        onSecondaryButtonClick = { basicDialog.dismiss() }
    )

    MonoAlertDialog(
        state = basicSingleButtonDialog,
        title = "Basic Dialog",
        text = "This is basic alert dialog with title, text and single button. This does not include an icon.",
        secondaryButton = "Cancel",
        onSecondaryButtonClick = { basicSingleButtonDialog.dismiss() }
    )

    MonoAlertDialog(
        state = fullDialog,
        title = "Full Dialog",
        text = "This is full alert dialog with icon, title, text and buttons",
        icon = { MonoIcon(PreviewIcons.Customize, Modifier.size(42.dp)) },
        primaryButton = "Ok",
        onPrimaryButtonClick = { fullDialog.dismiss() },
        secondaryButton = "Cancel",
        onSecondaryButtonClick = { fullDialog.dismiss() }
    )

    MonoAlertDialog(
        state = textDialog,
        text = "This is text alert dialog with text and single button. This does not include an icon, title and primary button.",
        secondaryButton = "Cancel",
        onSecondaryButtonClick = { textDialog.dismiss() }
    )

    MonoAlertDialog(
        state = noButtonDialog,
        title = "No Button Dialog",
        text = "This is basic alert dialog with title and text. This does not include an icon and buttons.",
    )

    MonoAlertDialog(
        state = onlyTextDialog,
        text = "This is basic alert dialog with text only. This does not include an icon, title and buttons.",
    )

    PreviewScaffold(
        onNavigate = onNavigate
    ) {
        PreviewContainerRow(
            title = "Mono Dialogs"
        ) {
            MonoButton(
                text = "Basic",
                onClick = { basicDialog.show() }
            )
            MonoButton(
                text = "Basic Single Button",
                onClick = { basicSingleButtonDialog.show() }
            )
            MonoButton(
                text = "Full",
                onClick = { fullDialog.show() }
            )
            MonoButton(
                text = "Text",
                onClick = { textDialog.show() }
            )
            MonoButton(
                text = "No Button",
                onClick = { noButtonDialog.show() }
            )
            MonoButton(
                text = "Only text",
                onClick = { onlyTextDialog.show() }
            )
        }
    }
}
