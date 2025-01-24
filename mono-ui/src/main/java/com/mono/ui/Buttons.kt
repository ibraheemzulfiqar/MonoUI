package com.mono.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mono.ui.internal.ProvideContentColorTextStyle

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
        LocalButtonConstraints provides constraints,
        LocalButtonShape provides shape,
    ) {
        MonoButton(
            modifier = modifier,
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
fun MonoToolbarButton(
    icon: MonoIcon,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = colorScheme.primary,
    contentColor: Color = contentColorFor(color),
    verticalSpace: Dp = 8.dp,
    iconSize: Dp = 24.dp,
    padding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    textStyle: TextStyle = LocalButtonTextStyle.current,
    interactionSource: MutableInteractionSource? = null,
) {
    val constraints = MonoButtonDefaults.constraints(
        minWidth = Dp.Unspecified,
        minHeight = Dp.Unspecified,
        padding = padding,
        iconSize = iconSize
    )

    CompositionLocalProvider(
        LocalButtonConstraints provides constraints,
        LocalButtonShape provides shape,
        LocalButtonTextStyle provides textStyle,
    ) {
        MonoButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            color = color,
            contentColor = contentColor,
            horizontalSpace = 0.dp,
            interactionSource = interactionSource,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    verticalSpace,
                    Alignment.CenterVertically
                )
            ) {
                MonoIcon(icon, Modifier.size(LocalButtonConstraints.current.iconSize))
                Text(text)
            }
        }
    }
}

@Composable
fun MonoElevatedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = LocalButtonShape.current,
    colors: ButtonColors = ButtonDefaults.elevatedButtonColors(),
    border: BorderStroke? = null,
    shadowElevation: Dp = 1.dp,
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
fun MonoOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = LocalButtonShape.current,
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
    shape: Shape = LocalButtonShape.current,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    shadowElevation: Dp = 0.dp,
    indication: Indication? = ripple(),
) {
    val color = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    MonoButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        color = color,
        contentColor = contentColor,
        border = border,
        shadowElevation = shadowElevation,
        indication = indication,
    ) {
        Text(text = text, color = contentColor)
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
    shape: Shape = CircleShape,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
) {
    val color = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    CompositionLocalProvider(
        LocalButtonConstraints provides LocalIconButtonConstraints.current,
        LocalButtonShape provides shape,
    ) {
        MonoButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
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
fun MonoBasicButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = Color.Transparent,
    contentColor: Color = LocalContentColor.current,
    horizontalSpace: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    contentPadding: PaddingValues? = null,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = ripple(),
    content: @Composable RowScope.() -> Unit,
) {
    ProvideButtonConstraints {
        MonoButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            color = color,
            contentColor = contentColor,
            horizontalSpace = horizontalSpace,
            shadowElevation = shadowElevation,
            border = border,
            contentPadding = contentPadding,
            interactionSource = interactionSource,
            indication = indication,
            content = content,
        )
    }
}

@Composable
fun MonoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = LocalButtonShape.current,
    color: Color = colorScheme.primary,
    contentColor: Color = contentColorFor(color),
    horizontalSpace: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    contentPadding: PaddingValues? = null,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = ripple(),
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
        indication = indication,
    ) {
        val mergedStyle = if (contentColor.isSpecified) {
            LocalButtonTextStyle.current.copy(color = contentColor)
        } else {
            LocalButtonTextStyle.current
        }

        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides mergedStyle,
        ) {
            val constraints = LocalButtonConstraints.current

            Row(
                Modifier
                    .defaultMinSize(
                        minWidth = constraints.minWidth,
                        minHeight = constraints.minHeight,
                    )
                    .padding(contentPadding ?: constraints.padding),
                horizontalArrangement = Arrangement.spacedBy(
                    horizontalSpace,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}


object MonoButtonDefaults {

    val FloatingActionButtonElevation = 6.dp

    internal const val DisabledOutlineOpacity = 0.12f
    internal val ListButtonDisabledOpacity = 0.38f

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

    val shape = RoundedCornerShape(100)

    fun fixedConstraints(
        all: Dp
    ) = constraints(
        minHeight = all,
        minWidth = all,
        iconSize = all,
        padding = PaddingValues()
    )

    fun constraints(
        minWidth: Dp = 58.dp,
        minHeight: Dp = 40.dp,
        iconSize: Dp = 24.dp,
        padding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    ) = ButtonConstraints(
        minWidth = minWidth,
        minHeight = minHeight,
        iconSize = iconSize,
        padding = padding,
    )

    fun noConstraints(
        minWidth: Dp = Dp.Unspecified,
        minHeight: Dp = Dp.Unspecified,
        iconSize: Dp = Dp.Unspecified,
        padding: PaddingValues = PaddingValues(),
    ) = ButtonConstraints(
        minWidth = minWidth,
        minHeight = minHeight,
        iconSize = iconSize,
        padding = padding,
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

val LocalButtonShape = compositionLocalOf<Shape> {
    MonoButtonDefaults.shape
}

val LocalButtonTextStyle = compositionLocalOf<TextStyle> {
    MonoButtonDefaults.textStyle
}

val LocalButtonConstraints = compositionLocalOf<ButtonConstraints> {
    MonoButtonDefaults.constraints()
}

val LocalIconButtonConstraints = compositionLocalOf<ButtonConstraints> {
    MonoButtonDefaults.iconButtonConstraints()
}