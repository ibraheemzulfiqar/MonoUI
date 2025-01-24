@file:OptIn(ExperimentalLayoutApi::class)

package com.mono.ui.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mono.ui.LocalButtonShape
import com.mono.ui.MonoBasicButton
import com.mono.ui.MonoButton
import com.mono.ui.MonoElevatedButton
import com.mono.ui.MonoIcon
import com.mono.ui.MonoIconButton
import com.mono.ui.MonoOutlinedButton
import com.mono.ui.MonoOutlinedIconButton
import com.mono.ui.MonoToolbarButton
import com.mono.ui.designsystem.components.PreviewContainerRow
import com.mono.ui.designsystem.components.PreviewScaffold
import com.mono.ui.designsystem.icons.PreviewIcons
import kotlinx.serialization.Serializable

@Serializable
object ButtonPreviewScreen

fun NavGraphBuilder.buttonPreviewScreenRoute(
    navController: NavHostController
) {
    composable<ButtonPreviewScreen> {
        ButtonPreviewScreen(
            onNavigate = { navController.navigateUp() }
        )
    }
}

@Preview
@Composable
fun ButtonPreviewScreen(
    onNavigate: () -> Unit = {},
) {
    PreviewScaffold(
        onNavigate = onNavigate,
    ) {
        PreviewContainerRow(
            title = "Mono Buttons"
        ) {
            MonoButton(
                text = "Button",
                onClick = {}
            )

            MonoElevatedButton(
                text = "Elevated Button",
                onClick = {},
            )

            MonoButton(
                text = "Tonal Button",
                onClick = {},
                colors = ButtonDefaults.filledTonalButtonColors(),
            )

            MonoButton(
                text = "Text Button",
                onClick = {},
                colors = ButtonDefaults.textButtonColors(),
            )

            MonoOutlinedButton(
                text = "Outlined Button",
                onClick = {},
            )
        }

        PreviewContainerRow(
            title = "Mono Icon Buttons"
        ) {
            MonoIconButton(
                icon = PreviewIcons.Call,
                onClick = {}
            )

            MonoOutlinedIconButton(
                icon = PreviewIcons.Call,
                onClick = {},
            )

            MonoIconButton(
                icon = PreviewIcons.Call,
                onClick = {},
                colors = IconButtonDefaults.filledIconButtonColors(),
            )

            MonoIconButton(
                icon = PreviewIcons.Call,
                onClick = {},
                colors = IconButtonDefaults.filledTonalIconButtonColors(),
            )
        }

        PreviewContainerRow(
            title = "Custom Buttons"
        ) {
            Row(
                modifier = Modifier.clip(LocalButtonShape.current)
            ) {
                MonoIconButton(
                    icon = PreviewIcons.Copy,
                    onClick = {},
                    shape = RectangleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(),
                )
                MonoIconButton(
                    icon = PreviewIcons.Share,
                    onClick = {},
                    shape = RectangleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(),
                )
            }

            Row {
                MonoToolbarButton(
                    modifier = Modifier.weight(1f),
                    icon = PreviewIcons.Share,
                    text = "Share",
                    onClick = {}
                )
                MonoToolbarButton(
                    modifier = Modifier.weight(1f),
                    icon = PreviewIcons.Copy,
                    text = "Copy",
                    onClick = {}
                )
                MonoToolbarButton(
                    modifier = Modifier.weight(1f),
                    icon = PreviewIcons.Delete,
                    text = "Delete",
                    onClick = {}
                )
            }

            var notifications by remember { mutableStateOf(false) }

            MonoBasicButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { notifications = !notifications },
                horizontalSpace = 12.dp,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            ) {
                MonoIcon(
                    icon = PreviewIcons.Notifications,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "Notification",
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                )
                Switch(
                    checked = notifications,
                    onCheckedChange = null
                )
            }
        }
    }
}