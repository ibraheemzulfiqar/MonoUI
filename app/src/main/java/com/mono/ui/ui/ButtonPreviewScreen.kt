@file:OptIn(ExperimentalLayoutApi::class)

package com.mono.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mono.ui.LocalButtonConstraints
import com.mono.ui.LocalButtonShape
import com.mono.ui.MonoButton
import com.mono.ui.MonoButtonDefaults
import com.mono.ui.MonoElevatedButton
import com.mono.ui.MonoIcon
import com.mono.ui.MonoIconButton
import com.mono.ui.MonoOutlinedButton
import com.mono.ui.MonoOutlinedIconButton
import com.mono.ui.MonoToolbarButton
import com.mono.ui.designsystem.components.PreviewContainerRow
import com.mono.ui.designsystem.components.PreviewScaffold
import com.mono.ui.designsystem.icons.MonoIcons
import kotlinx.serialization.Serializable

@Serializable
object ButtonPreviewScreen

fun NavGraphBuilder.buttonPreviewScreenRoute(
    navHostController: NavHostController
) {
    composable<ButtonPreviewScreen> {
        ButtonPreviewScreen()
    }
}

@Preview
@Composable
fun ButtonPreviewScreen() {
    PreviewScaffold {
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
                icon = MonoIcons.Call,
                onClick = {}
            )

            MonoOutlinedIconButton(
                icon = MonoIcons.Call,
                onClick = {},
            )

            MonoIconButton(
                icon = MonoIcons.Call,
                onClick = {},
                colors = IconButtonDefaults.filledIconButtonColors(),
            )

            MonoIconButton(
                icon = MonoIcons.Call,
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
                    icon = MonoIcons.Copy,
                    onClick = {},
                    shape = RectangleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(),
                )
                MonoIconButton(
                    icon = MonoIcons.Share,
                    onClick = {},
                    shape = RectangleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(),
                )
            }

            Row {
                MonoToolbarButton(
                    modifier = Modifier.weight(1f),
                    icon = MonoIcons.Share,
                    text = "Share",
                    onClick = {}
                )
                MonoToolbarButton(
                    modifier = Modifier.weight(1f),
                    icon = MonoIcons.Copy,
                    text = "Copy",
                    onClick = {}
                )
                MonoToolbarButton(
                    modifier = Modifier.weight(1f),
                    icon = MonoIcons.Delete,
                    text = "Delete",
                    onClick = {}
                )
            }

            MonoButton(
                onClick = {},
                horizontalSpace = 8.dp,
            ) {
                MonoIcon(MonoIcons.Share)
                Text(text = "Share")
            }
        }
    }
}