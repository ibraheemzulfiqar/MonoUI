package com.mono.ui

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

interface UiProfile {
    val buttonShape: Shape
    val buttonConstraints: ButtonConstraints
    val iconButtonConstraints: ButtonConstraints
    val buttonTextStyle: TextStyle
}

open class MobileUiProfile : UiProfile {
    override val buttonShape: Shape = MonoButtonDefaults.shape
    override val buttonConstraints: ButtonConstraints = MonoButtonDefaults.constraints()
    override val iconButtonConstraints: ButtonConstraints = MonoButtonDefaults.iconButtonConstraints()
    override val buttonTextStyle: TextStyle = MonoButtonDefaults.textStyle
}