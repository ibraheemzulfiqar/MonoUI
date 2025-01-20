package com.mono.ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mono.ui.LocalButtonShape
import com.mono.ui.MonoButton
import com.mono.ui.MonoButtonDefaults
import com.mono.ui.MonoFloatingActionButton
import com.mono.ui.MonoOutlinedButton
import com.mono.ui.designsystem.components.PreviewContainerRow
import com.mono.ui.designsystem.icons.MonoIcons
import com.mono.ui.designsystem.theme.MonoUITheme

@Composable
fun PreviewApp() {
    val navController = rememberNavController()
    var buttonShape: Shape by remember { mutableStateOf(MonoButtonDefaults.shape) }
    var customizationDialogVisible by remember { mutableStateOf(false) }

    MonoUITheme {

        CompositionLocalProvider(
            LocalButtonShape provides buttonShape,
        ) {
            if (customizationDialogVisible) {
                CustomizationBottomSheet(
                    onDismissRequest = { customizationDialogVisible = false },
                    onUpdateShape = { buttonShape = it }
                )
            }

            Scaffold(
                floatingActionButton = {
                    MonoFloatingActionButton(
                        icon = MonoIcons.Customize,
                        constraints = MonoButtonDefaults.largeFabConstraints(),
                        onClick = { customizationDialogVisible = true },
                    )
                }
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = HomeScreen
                ) {
                    homeScreenRoute(navController)
                    buttonPreviewScreenRoute(navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CustomizationBottomSheet(
    onUpdateShape: (Shape) -> Unit,
    onDismissRequest: () -> Unit,
) {
    @Composable
    fun SelectableButton(
        text:String,
        isSelected:Boolean,
        onClick: () -> Unit,
    ) {
        MonoButton(
            text = text,
            colors = if (isSelected) ButtonDefaults.buttonColors() else ButtonDefaults.outlinedButtonColors(),
            border = if (isSelected) null else MonoButtonDefaults.outlinedBorder(),
            onClick = onClick,
        )
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest
    ) {
        Text(
            text = "Customize UI",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        PreviewContainerRow(
            title = "Corner radius"
        ) {
            val fullShape = RoundedCornerShape(100)
            val smallShape = RoundedCornerShape(4.dp)
            val rectShape = RectangleShape
            val currentShape = LocalButtonShape.current

            SelectableButton(
                text = "Full",
                isSelected = currentShape == fullShape,
                onClick = {
                    onUpdateShape(fullShape)
                }
            )

            SelectableButton(
                text = "Small",
                isSelected = currentShape == smallShape,
                onClick = {
                    onUpdateShape(smallShape)
                }
            )

            SelectableButton(
                text = "None",
                isSelected = currentShape == rectShape,
                onClick = {
                    onUpdateShape(rectShape)
                }
            )
        }
    }
}