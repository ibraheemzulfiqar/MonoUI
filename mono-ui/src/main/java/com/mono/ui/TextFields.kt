package com.mono.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mono.ui.MonoTextFieldDefaults.DisabledOpacity
import com.mono.ui.MonoTextFieldDefaults.MonoTextFieldColors
import com.mono.ui.MonoTextFieldDefaults.colors
import com.mono.ui.MonoTextFieldDefaults.horizontalSpacing

@Composable
fun MonoTextField(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    startIcon: MonoIcon? = null,
    endIcon: MonoIcon? = null,
    onEndIconClick: (() -> Unit)? = null,
    onStartIconClick: (() -> Unit)? = null,
    colors: MonoTextFieldColors = colors(),
    shape: Shape = MonoTextFieldDefaults.shape,
    contentPadding: PaddingValues = MonoTextFieldDefaults.iconBasePadding(
        hasStartIcon = startIcon != null,
        hasEndIcon = endIcon != null,
    ),
    inputTransformation: InputTransformation? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    outputTransformation: OutputTransformation? = null,
    scrollState: ScrollState = rememberScrollState(),
) {
    val direction = LocalLayoutDirection.current
    val (iconMinWidth, iconMinHeight) = with(LocalIconButtonConstraints.current) {
        val width =
            minWidth + padding.calculateStartPadding(direction) + padding.calculateEndPadding(direction)
        val height = minHeight + padding.calculateTopPadding() + padding.calculateBottomPadding()

        width to height
    }

    @Composable
    fun TextFieldIconButton(
        icon: MonoIcon?,
        onClick: (() -> Unit)?
    ) {
        if (icon == null) return

        if (onClick == null) {
            Box(
                modifier = Modifier.sizeIn(minWidth = iconMinWidth, minHeight = iconMinHeight),
                contentAlignment = Alignment.Center,
            ) {
                MonoIcon(icon)
            }
        } else {
            MonoIconButton(
                icon = icon,
                onClick = onClick,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = colors.endIcon,
                    disabledContentColor = colors.endIcon.copy(alpha = DisabledOpacity),
                )
            )
        }
    }

    val startIconContent: @Composable () -> Unit = {
        TextFieldIconButton(startIcon, onStartIconClick)
    }

    val endIconContent: @Composable () -> Unit = {
        TextFieldIconButton(endIcon, onEndIconClick)
    }

    MonoTextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        placeholder = { Text(placeholder) },
        startIcon = startIconContent.takeIf { startIcon != null },
        endIcon = endIconContent.takeIf { endIcon != null },
        colors = colors,
        shape = shape,
        contentPadding = contentPadding,
        inputTransformation = inputTransformation,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        outputTransformation = outputTransformation,
        scrollState = scrollState,
        horizontalSpace = 0.dp,
    )
}

@Composable
fun MonoTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    placeholder: @Composable (() -> Unit)? = null,
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
    colors: MonoTextFieldColors = colors(),
    shape: Shape = MonoTextFieldDefaults.shape,
    horizontalSpace: Dp = horizontalSpacing,
    contentPadding: PaddingValues = MonoTextFieldDefaults.contentPadding,
    inputTransformation: InputTransformation? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    outputTransformation: OutputTransformation? = null,
    scrollState: ScrollState = rememberScrollState(),
) {
    val contentColor =
        if (enabled) colors.content else colors.content.copy(alpha = DisabledOpacity)
    val mergedTextStyle = textStyle.merge(TextStyle(color = colors.content))

    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = colors.cursor,
            backgroundColor = colors.cursorHighlight
        ),
        LocalContentColor provides contentColor,
        LocalTextStyle provides mergedTextStyle
    ) {
        BasicTextField(
            state = state,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            inputTransformation = inputTransformation,
            textStyle = mergedTextStyle,
            keyboardOptions = keyboardOptions,
            onKeyboardAction = onKeyboardAction,
            lineLimits = lineLimits,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(colors.cursor),
            outputTransformation = outputTransformation,
            scrollState = scrollState,
            decorator = { innerTextField ->
                MonoTextFieldDefaults.DecorationBox(
                    value = state.text.toString(),
                    innerTextField = innerTextField,
                    placeholder = placeholder,
                    leadingIcon = startIcon,
                    trailingIcon = endIcon,
                    colors = colors,
                    enabled = enabled,
                    shape = shape,
                    padding = contentPadding,
                    horizontalSpace = horizontalSpace,
                )
            }
        )
    }
}

object MonoTextFieldDefaults {

    val MinHeight = 56.dp
    val MinWidth = 280.dp

    val shape = RoundedCornerShape(12.dp)
    val contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
    val horizontalSpacing = 8.dp

    internal const val DisabledOpacity = 0.12f

    @Composable
    fun DecorationBox(
        value: String,
        innerTextField: @Composable () -> Unit,
        placeholder: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        horizontalSpace: Dp = horizontalSpacing,
        enabled: Boolean = true,
        shape: Shape = MonoTextFieldDefaults.shape,
        padding: PaddingValues = contentPadding,
        colors: MonoTextFieldColors = colors(),
    ) {
        val placeholderColor = colorEnabled(colors.placeholder, enabled)
        val leadingIconColor = colorEnabled(colors.startIcon, enabled)
        val trailingIconColor = colorEnabled(colors.endIcon, enabled)

        Row(
            modifier = Modifier
                .clip(shape)
                .background(colors.container)
                .padding(padding),
            horizontalArrangement = Arrangement.spacedBy(horizontalSpace),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leadingIcon != null) {
                CompositionLocalProvider(LocalContentColor provides leadingIconColor) {
                    leadingIcon()
                }
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart,
            ) {
                innerTextField()

                CompositionLocalProvider(LocalContentColor provides placeholderColor) {
                    if (value.isEmpty() && placeholder != null) {
                        placeholder()
                    }
                }
            }

            if (trailingIcon != null) {
                CompositionLocalProvider(LocalContentColor provides trailingIconColor) {
                    trailingIcon()
                }
            }
        }
    }

    internal fun iconBasePadding(
        hasStartIcon: Boolean,
        hasEndIcon: Boolean,
    ) = PaddingValues(
        start = if (hasStartIcon) 8.dp else 20.dp,
        end = if (hasEndIcon) 8.dp else 20.dp,
        top = if (hasStartIcon || hasEndIcon) 4.dp else 12.dp,
        bottom = if (hasStartIcon || hasEndIcon) 4.dp else 12.dp,
    )

    @Composable
    fun colors(
        container: Color = colorScheme.surfaceContainer,
        content: Color = colorScheme.onSurface,
        placeholder: Color = colorScheme.onSurfaceVariant,
        startIcon: Color = colorScheme.onSurfaceVariant,
        endIcon: Color = colorScheme.onSurfaceVariant,
        cursor: Color = colorScheme.primary,
        cursorHighlight: Color = colorScheme.primary.copy(alpha = 0.4f),
    ) = MonoTextFieldColors(
        container = container,
        content = content,
        cursor = cursor,
        placeholder = placeholder,
        cursorHighlight = cursorHighlight,
        startIcon = startIcon,
        endIcon = endIcon,
    )


    internal fun colorEnabled(color: Color, enabled: Boolean): Color {
        return if (enabled) color else color.copy(alpha = DisabledOpacity)
    }

    data class MonoTextFieldColors(
        val container: Color,
        val content: Color,
        val placeholder: Color,
        val startIcon: Color,
        val endIcon: Color,
        val cursor: Color,
        val cursorHighlight: Color,
    )
}