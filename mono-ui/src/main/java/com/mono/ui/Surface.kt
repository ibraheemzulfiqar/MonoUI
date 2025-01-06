package com.mono.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTonalElevationEnabled
import androidx.compose.material3.LocalUseFallbackRippleImplementation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
@NonRestartableComposable
fun MonoSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    val absoluteElevation = LocalAbsoluteTonalElevation.current + tonalElevation
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteTonalElevation provides absoluteElevation
    ) {
        Box(
            modifier =
                modifier
                    .surface(
                        shape = shape,
                        backgroundColor =
                            surfaceColorAtElevation(color = color, elevation = absoluteElevation),
                        border = border,
                        shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() }
                    )
                    .semantics(mergeDescendants = false) {
                        @Suppress("DEPRECATION")
                        isContainer = true
                    }
                    .pointerInput(Unit) {},
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Composable
@NonRestartableComposable
fun MonoSurface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {
    val absoluteElevation = LocalAbsoluteTonalElevation.current + tonalElevation
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteTonalElevation provides absoluteElevation
    ) {
        Box(
            modifier =
                modifier
//                    .minimumInteractiveComponentSize()
                    .surface(
                        shape = shape,
                        backgroundColor =
                            surfaceColorAtElevation(color = color, elevation = absoluteElevation),
                        border = border,
                        shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() }
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = rippleOrFallbackImplementation(),
                        enabled = enabled,
                        onClick = onClick
                    ),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Composable
@NonRestartableComposable
fun MonoSurface(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {
    val absoluteElevation = LocalAbsoluteTonalElevation.current + tonalElevation
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteTonalElevation provides absoluteElevation
    ) {
        Box(
            modifier =
                modifier
                    .minimumInteractiveComponentSize()
                    .surface(
                        shape = shape,
                        backgroundColor =
                            surfaceColorAtElevation(color = color, elevation = absoluteElevation),
                        border = border,
                        shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() }
                    )
                    .selectable(
                        selected = selected,
                        interactionSource = interactionSource,
                        indication = rippleOrFallbackImplementation(),
                        enabled = enabled,
                        onClick = onClick
                    ),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Composable
@NonRestartableComposable
fun MonoSurface(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {
    val absoluteElevation = LocalAbsoluteTonalElevation.current + tonalElevation
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteTonalElevation provides absoluteElevation
    ) {
        Box(
            modifier =
                modifier
                    .minimumInteractiveComponentSize()
                    .surface(
                        shape = shape,
                        backgroundColor =
                            surfaceColorAtElevation(color = color, elevation = absoluteElevation),
                        border = border,
                        shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() }
                    )
                    .toggleable(
                        value = checked,
                        interactionSource = interactionSource,
                        indication = rippleOrFallbackImplementation(),
                        enabled = enabled,
                        onValueChange = onCheckedChange
                    ),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Stable
private fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke?,
    shadowElevation: Float,
) =
    this.then(
        if (shadowElevation > 0f) {
            Modifier.graphicsLayer(
                shadowElevation = shadowElevation,
                shape = shape,
                clip = false
            )
        } else {
            Modifier
        }
    )
        .then(if (border != null) Modifier.border(border, shape) else Modifier)
        .background(color = backgroundColor, shape = shape)
        .clip(shape)

@Composable
private fun surfaceColorAtElevation(color: Color, elevation: Dp): Color =
    MaterialTheme.colorScheme.applyTonalElevation(color, elevation)


@Composable
@ReadOnlyComposable
internal fun ColorScheme.applyTonalElevation(backgroundColor: Color, elevation: Dp): Color {
    val tonalElevationEnabled = LocalTonalElevationEnabled.current
    return if (backgroundColor == surface && tonalElevationEnabled) {
        surfaceColorAtElevation(elevation)
    } else {
        backgroundColor
    }
}

// TODO: b/304985887 - remove after one stable release
@Suppress("DEPRECATION_ERROR")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun rippleOrFallbackImplementation(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified
): Indication {
    return if (LocalUseFallbackRippleImplementation.current) {
        androidx.compose.material.ripple.rememberRipple(bounded, radius, color)
    } else {
        ripple(bounded, radius, color)
    }
}

/**
 * CompositionLocal containing the current absolute elevation provided by [MonoSurface] components. This
 * absolute elevation is a sum of all the previous elevations. Absolute elevation is only used for
 * calculating surface tonal colors, and is *not* used for drawing the shadow in a [MonoSurface].
 */
// TODO(b/179787782): Add sample after catalog app lands in aosp.
val LocalAbsoluteTonalElevation = compositionLocalOf { 0.dp }
