package com.mono.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.Dp

@Composable
fun ProvideButtonConstraints(
    minWidth: Dp = Dp.Unspecified,
    minHeight: Dp = Dp.Unspecified,
    padding: PaddingValues = PaddingValues(),
    iconSize: Dp = Dp.Unspecified,
    content: @Composable () -> Unit
) {
    val constraints = ButtonConstraints(
        minWidth = minWidth,
        minHeight = minHeight,
        padding = padding,
        iconSize = iconSize
    )

    CompositionLocalProvider(
        LocalButtonConstraints provides constraints,
        content = content,
    )
}

@Composable
fun ProvideUiProfile(
    uiProfile: UiProfile,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalButtonConstraints provides uiProfile.buttonConstraints,
        LocalButtonShape provides uiProfile.buttonShape,
        LocalButtonTextStyle provides uiProfile.buttonTextStyle,
        LocalIconButtonConstraints provides uiProfile.iconButtonConstraints,
        content = content,
    )
}

