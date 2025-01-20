package com.mono.ui.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mono.ui.MonoButton
import com.mono.ui.designsystem.components.PreviewScaffold
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

fun NavGraphBuilder.homeScreenRoute(
    navHostController: NavHostController,
) {
    composable<HomeScreen> {
        HomeScreenContent(
            onNavigateToButtonPreview = {
                navHostController.navigate(ButtonPreviewScreen)
            }
        )
    }
}

@Composable
fun HomeScreenContent(
    onNavigateToButtonPreview: () -> Unit,
) {
    PreviewScaffold(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        Text(
            text = "MonoUI",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        MonoButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Buttons",
            onClick = onNavigateToButtonPreview,
        )
    }
}