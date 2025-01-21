package com.mono.ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mono.ui.MonoButton
import com.mono.ui.MonoMenuButton
import com.mono.ui.MonoMenuItem
import com.mono.ui.designsystem.components.PreviewScaffold
import com.mono.ui.designsystem.icons.PreviewIcons
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

fun NavGraphBuilder.homeScreenRoute(
    navController: NavHostController,
) {
    composable<HomeScreen> {
        HomeScreen(
            onNavigateToButtonPreview = {
                navController.navigate(ButtonPreviewScreen)
            },
            onNavigateToDialogPreview = {
                navController.navigate(DialogPreviewScreen)
            }
        )
    }
}

@Composable
fun HomeScreen(
    onNavigateToButtonPreview: () -> Unit,
    onNavigateToDialogPreview: () -> Unit,
) {
    PreviewScaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        title = "MonoUI",
        actions = { HomeMenu() },
    ) {
        MonoButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Buttons",
            onClick = onNavigateToButtonPreview,
        )

        MonoButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Dialogs",
            onClick = onNavigateToDialogPreview,
        )
    }
}

@Composable
fun HomeMenu(modifier: Modifier = Modifier) {
    MonoMenuButton(
        modifier = modifier,
        icon = PreviewIcons.Menu,
    ) {
        MonoMenuItem(
            text = "Settings",
            leadingIcon = PreviewIcons.Settings,
            onClick = {},
        )
        MonoMenuItem(
            text = "Share",
            leadingIcon = PreviewIcons.Share,
            onClick = {},
        )
        MonoMenuItem(
            text = "Feedback",
            leadingIcon = PreviewIcons.Feedback,
            onClick = {},
        )
    }
}