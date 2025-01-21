package com.mono.ui

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.min

@Composable
fun MonoMenuButton(
    icon: MonoIcon,
    modifier: Modifier = Modifier,
    state: DismissibleState = rememberDismissibleState(),
    alignment: Alignment = Alignment.TopEnd,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    scrollState: ScrollState = rememberScrollState(),
    properties: PopupProperties = PopupProperties(focusable = true),
    shape: Shape = MenuDefaults.shape,
    containerColor: Color = MenuDefaults.containerColor,
    shadowElevation: Dp = MenuDefaults.ShadowElevation,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = modifier.wrapContentSize(alignment)
    ) {
        MonoIconButton(
            icon = icon,
            onClick = { state.show() }
        )
        MonoDropDownMenu(
            expanded = state.visible,
            onDismissRequest = { state.dismiss() },
            offset = offset,
            scrollState = scrollState,
            properties = properties,
            shape = shape,
            containerColor = containerColor,
            shadowElevation = shadowElevation,
            border = border,
            content = content,
        )
    }
}

@Composable
fun MonoMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: MonoIcon? = null,
    trailingIcon: MonoIcon? = null,
    enabled: Boolean = true,
    textStyle: TextStyle = typography.labelLarge,
    colors: MenuItemColors = MenuDefaults.itemColors(),
    shape: Shape = RectangleShape,
    horizontalSpace: Dp = DropdownMenuItemHorizontalPadding,
    constraints: ButtonConstraints = MonoMenuDefaults.itemConstraints(),
    interactionSource: MutableInteractionSource? = null,
) {
    CompositionLocalProvider(
        LocalButtonShape provides shape,
        LocalButtonConstraints provides constraints,
        LocalTextStyle provides textStyle,
    ) {
        val iconSize = LocalButtonConstraints.current.iconSize

        MonoButton(
            modifier = modifier.widthIn(max = DropdownMenuItemDefaultMaxWidth),
            onClick = onClick,
            color = Color.Transparent,
            contentColor = Color.Unspecified,
            horizontalSpace = horizontalSpace,
            interactionSource = interactionSource,
        ) {
            if (leadingIcon != null) {
                MonoIcon(
                    modifier = Modifier.size(iconSize),
                    icon = leadingIcon,
                    tint = if (enabled) colors.leadingIconColor else colors.disabledLeadingIconColor,
                )
            }

            Text(
                modifier = Modifier.weight(1f),
                text = text,
            )

            if (trailingIcon != null) {
                MonoIcon(
                    modifier = Modifier.size(iconSize),
                    icon = trailingIcon,
                    tint = if (enabled) colors.trailingIconColor else colors.disabledTrailingIconColor,
                )
            }
        }
    }
}

@Composable
fun MonoDropDownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    scrollState: ScrollState = rememberScrollState(),
    properties: PopupProperties = PopupProperties(focusable = true),
    shape: Shape = MenuDefaults.shape,
    containerColor: Color = MenuDefaults.containerColor,
    shadowElevation: Dp = MenuDefaults.ShadowElevation,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val expandedState = remember { MutableTransitionState(false) }
    expandedState.targetState = expanded

    if (expandedState.currentState || expandedState.targetState) {
        val transformOriginState = remember { mutableStateOf(TransformOrigin.Center) }
        val density = LocalDensity.current
        val popupPositionProvider =
            remember(offset, density) {
                DropdownMenuPositionProvider(offset, density) { parentBounds, menuBounds ->
                    transformOriginState.value = calculateTransformOrigin(parentBounds, menuBounds)
                }
            }

        Popup(
            onDismissRequest = onDismissRequest,
            popupPositionProvider = popupPositionProvider,
            properties = properties
        ) {
            DropdownMenuContent(
                expandedState = expandedState,
                transformOriginState = transformOriginState,
                scrollState = scrollState,
                shape = shape,
                containerColor = containerColor,
                shadowElevation = shadowElevation,
                border = border,
                modifier = modifier,
                content = content,
            )
        }
    }
}

@Composable
internal fun DropdownMenuContent(
    modifier: Modifier,
    expandedState: MutableTransitionState<Boolean>,
    transformOriginState: MutableState<TransformOrigin>,
    scrollState: ScrollState,
    shape: Shape,
    containerColor: Color,
    shadowElevation: Dp,
    border: BorderStroke?,
    content: @Composable ColumnScope.() -> Unit
) {
    // Menu open/close animation.
    @Suppress("DEPRECATION") val transition = updateTransition(expandedState, "DropDownMenu")

    val scale by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded
                tween(durationMillis = InTransitionDuration, easing = LinearOutSlowInEasing)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = 1, delayMillis = OutTransitionDuration - 1)
            }
        }
    ) { expanded ->
        if (expanded) ExpandedScaleTarget else ClosedScaleTarget
    }

    val alpha by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded
                tween(durationMillis = 30)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = OutTransitionDuration)
            }
        }
    ) { expanded ->
        if (expanded) ExpandedAlphaTarget else ClosedAlphaTarget
    }

    val isInspecting = LocalInspectionMode.current

    MonoSurface(
        modifier =
            Modifier.graphicsLayer {
                scaleX =
                    if (!isInspecting) scale
                    else if (expandedState.targetState) ExpandedScaleTarget else ClosedScaleTarget
                scaleY =
                    if (!isInspecting) scale
                    else if (expandedState.targetState) ExpandedScaleTarget else ClosedScaleTarget
                this.alpha =
                    if (!isInspecting) alpha
                    else if (expandedState.targetState) ExpandedAlphaTarget else ClosedAlphaTarget
                transformOrigin = transformOriginState.value
            },
        shape = shape,
        color = containerColor,
        shadowElevation = shadowElevation,
        border = border,
    ) {
        Column(
            modifier =
                modifier
                    .width(IntrinsicSize.Max)
                    .verticalScroll(scrollState),
            content = content
        )
    }
}

internal fun calculateTransformOrigin(anchorBounds: IntRect, menuBounds: IntRect): TransformOrigin {
    val pivotX =
        when {
            menuBounds.left >= anchorBounds.right -> 0f
            menuBounds.right <= anchorBounds.left -> 1f
            menuBounds.width == 0 -> 0f
            else -> {
                val intersectionCenter =
                    (max(anchorBounds.left, menuBounds.left) +
                            min(anchorBounds.right, menuBounds.right)) / 2
                (intersectionCenter - menuBounds.left).toFloat() / menuBounds.width
            }
        }
    val pivotY =
        when {
            menuBounds.top >= anchorBounds.bottom -> 0f
            menuBounds.bottom <= anchorBounds.top -> 1f
            menuBounds.height == 0 -> 0f
            else -> {
                val intersectionCenter =
                    (max(anchorBounds.top, menuBounds.top) +
                            min(anchorBounds.bottom, menuBounds.bottom)) / 2
                (intersectionCenter - menuBounds.top).toFloat() / menuBounds.height
            }
        }
    return TransformOrigin(pivotX, pivotY)
}

object MonoMenuDefaults {

    fun itemConstraints(
        minWidth: Dp = DropdownMenuItemDefaultMinWidth,
        minHeight: Dp = MenuListItemContainerHeight,
        iconSize: Dp = 24.dp,
        padding: PaddingValues = PaddingValues(
            horizontal = DropdownMenuItemHorizontalPadding,
            vertical = DropdownMenuVerticalPadding
        )
    ) = ButtonConstraints(
        minWidth = minWidth,
        minHeight = minHeight,
        iconSize = iconSize,
        padding = padding,
    )

}

// Size defaults.
internal val MenuVerticalMargin = 0.dp
private val MenuListItemContainerHeight = 48.dp
private val DropdownMenuItemHorizontalPadding = 12.dp
internal val DropdownMenuVerticalPadding = 8.dp
private val DropdownMenuItemDefaultMinWidth = 112.dp
private val DropdownMenuItemDefaultMaxWidth = 280.dp

// Menu open/close animation.
internal const val InTransitionDuration = 120
internal const val OutTransitionDuration = 75
internal const val ExpandedScaleTarget = 1f
internal const val ClosedScaleTarget = 0.8f
internal const val ExpandedAlphaTarget = 1f
internal const val ClosedAlphaTarget = 0f