package com.mono.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
private fun ButtonPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {

        MonoButton(
            modifier = Modifier,
            text = "Default",
            onClick = {},
        )

        CompositionLocalProvider(
            LocalButtonConstraints provides MonoButtonDefaults.constraints(minHeight = 54.dp),
            LocalButtonTextStyle provides LocalButtonTextStyle.current.copy(fontSize = 18.sp)
        ) {
            MonoButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Custom",
                onClick = {},
            )
        }

        MonoIconButton(
            icon = rememberMonoIcon(Icons.Default.Call),
            onClick = {},
            colors = IconButtonDefaults.outlinedIconButtonColors()
        )

        MonoOutlinedIconButton(
            icon = rememberMonoIcon(Icons.Default.Call),
            onClick = {},
            colors = IconButtonDefaults.outlinedIconButtonColors()
        )

        MonoOutlinedButton(
            text = "Outlined",
            onClick = {},
        )

        MonoButton(
            onClick = {},
            color = IconButtonDefaults.outlinedIconButtonColors().containerColor,
            contentColor = colorScheme.primary,
            border = MonoButtonDefaults.outlinedBorder(),
            modifier = Modifier.heightIn(min = 54.dp)
        ) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
            Text("Add New Account", Modifier.padding(start = 8.dp))
        }

        MonoFloatingActionButton(
            icon = rememberMonoIcon(Icons.Default.Add),
            onClick = {},
        )

        MonoSmallFloatingActionButton(
            icon = rememberMonoIcon(Icons.Default.Add),
            onClick = {},
        )
    }
}

@Composable
fun MonoSmallFloatingActionButton(
    icon: MonoIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = FloatingActionButtonDefaults.smallShape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    shadowElevation: Dp = MonoButtonDefaults.FloatingActionButtonElevation,
    constraints: ButtonConstraints = MonoButtonDefaults.smallFabConstraints(),
    interactionSource: MutableInteractionSource? = null,
) {
    MonoFloatingActionButton(
        icon = icon,
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        shadowElevation = shadowElevation,
        constraints = constraints,
        interactionSource = interactionSource,
    )
}

@Composable
fun MonoFloatingActionButton(
    icon: MonoIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = FloatingActionButtonDefaults.shape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    shadowElevation: Dp = MonoButtonDefaults.FloatingActionButtonElevation,
    constraints: ButtonConstraints = MonoButtonDefaults.fabConstraints(),
    interactionSource: MutableInteractionSource? = null,
) {
    MonoFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        shadowElevation = shadowElevation,
        interactionSource = interactionSource,
        constraints = constraints,
    ) {
        MonoIcon(
            icon = icon,
            modifier = Modifier.size(constraints.iconSize),
        )
    }
}

@Composable
fun MonoFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = FloatingActionButtonDefaults.shape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    shadowElevation: Dp = MonoButtonDefaults.FloatingActionButtonElevation,
    constraints: ButtonConstraints = MonoButtonDefaults.fabConstraints(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    CompositionLocalProvider(
        LocalButtonConstraints provides constraints
    ) {
        MonoButton(
            modifier = modifier,
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            shadowElevation = shadowElevation,
            interactionSource = interactionSource,
            onClick = onClick,
            content = content,
        )
    }
}

@Composable
fun MonoOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    border: BorderStroke = MonoButtonDefaults.outlinedBorder(enabled),
    shadowElevation: Dp = 0.dp,
) {
    MonoButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        shadowElevation = shadowElevation,
        border = border,
    )
}

@Composable
fun MonoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    shadowElevation: Dp = 0.dp,
) {
    val color = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    MonoButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        color = color,
        border = border,
        contentColor = contentColor,
        shadowElevation = shadowElevation,
    ) {
        Text(text = text)
    }
}

@Composable
fun MonoOutlinedIconButton(
    icon: MonoIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.outlinedIconButtonColors(),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = MonoButtonDefaults.outlinedBorder(enabled),
) {
    MonoIconButton(
        icon = icon,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        shadowElevation = shadowElevation,
        border = border,
    )
}

@Composable
fun MonoIconButton(
    icon: MonoIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
) {
    val color = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    CompositionLocalProvider(
        LocalButtonConstraints provides LocalIconButtonConstraints.current
    ) {
        MonoButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = CircleShape,
            color = color,
            border = border,
            contentColor = contentColor,
            shadowElevation = shadowElevation,
        ) {
            val constraints = LocalIconButtonConstraints.current

            MonoIcon(
                icon = icon,
                modifier = Modifier.size(constraints.iconSize)
            )
        }
    }
}

@Composable
fun MonoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    color: Color = colorScheme.primary,
    contentColor: Color = contentColorFor(color),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit
) {
    MonoSurface(
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Button },
        enabled = enabled,
        shape = shape,
        color = color,
        contentColor = contentColor,
        shadowElevation = shadowElevation,
        border = border,
        interactionSource = interactionSource,
    ) {
        val mergedStyle = LocalTextStyle.current.merge(LocalButtonTextStyle.current)

        CompositionLocalProvider(
            LocalTextStyle provides mergedStyle,
        ) {
            val constraints = LocalButtonConstraints.current

            Row(
                Modifier
                    .sizeIn(
                        minWidth = constraints.minWidth,
                        minHeight = constraints.minHeight,
                    )
                    .padding(constraints.padding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}


object MonoButtonDefaults {

    val FloatingActionButtonElevation = 6.dp
    internal const val DisabledOutlineOpacity = 0.12f

    val textStyle = TextStyle.Default.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    )

    fun constraints(
        minWidth: Dp = 58.dp,
        minHeight: Dp = 40.dp,
        iconSize: Dp = 24.dp,
        padding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
    ) = ButtonConstraints(
        minWidth = minWidth,
        minHeight = minHeight,
        iconSize = iconSize,
        padding = padding,
    )

    fun constraintUnspecified() = constraints(
        minWidth = Dp.Unspecified,
        minHeight = Dp.Unspecified,
        iconSize = Dp.Unspecified,
        padding = PaddingValues(),
    )

    fun iconButtonConstraints() = constraints(
        minWidth = 40.dp,
        minHeight = 40.dp,
        iconSize = 24.dp,
        padding = PaddingValues(),
    )

    fun fabConstraints() = constraints(
        minWidth = 54.dp,
        minHeight = 54.dp,
        iconSize = 24.dp,
        padding = PaddingValues(),
    )

    fun largeFabConstraints() = constraints(
        minWidth = 96.dp,
        minHeight = 96.dp,
        iconSize = 24.dp,
        padding = PaddingValues(),
    )

    fun smallFabConstraints() = constraints(
        minWidth = 40.dp,
        minHeight = 40.dp,
        iconSize = 24.dp,
        padding = PaddingValues(),
    )

    @Composable
    fun outlinedBorder(enabled: Boolean = true) = BorderStroke(
        width = 1.dp,
        color = if (enabled) {
            colorScheme.outline
        } else {
            colorScheme.outline.copy(
                alpha = DisabledOutlineOpacity
            )
        }
    )
}

@Immutable
data class ButtonConstraints(
    val minWidth: Dp,
    val minHeight: Dp,
    val iconSize: Dp,
    val padding: PaddingValues,
)

val LocalButtonTextStyle = compositionLocalOf<TextStyle> {
    MonoButtonDefaults.textStyle
}

val LocalButtonConstraints = compositionLocalOf<ButtonConstraints> {
    MonoButtonDefaults.constraints()
}

val LocalIconButtonConstraints = compositionLocalOf<ButtonConstraints> {
    MonoButtonDefaults.iconButtonConstraints()
}