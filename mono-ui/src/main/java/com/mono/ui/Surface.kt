package com.mono.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


// #121212

@Composable
@NonRestartableComposable
fun MonoSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier =
                modifier
                    .surface(
                        shape = shape,
                        backgroundColor = color,
                        border = border,
                        shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() },
                    )
                    .semantics(mergeDescendants = false) {
                        isTraversalGroup = true
                    },
            propagateMinConstraints = false,
            contentAlignment = contentAlignment,
            content = content,
        )
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
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = ripple(),
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier =
                modifier
                    .surface(
                        shape = shape,
                        backgroundColor = color,
                        border = border,
                        shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() }
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = indication,
                        enabled = enabled,
                        onClick = onClick
                    ),
            propagateMinConstraints = false,
            contentAlignment = contentAlignment,
            content = content,
        )
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
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier =
                modifier
                    .surface(
                        shape = shape,
                        backgroundColor = color,
                        border = border,
                        shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() }
                    )
                    .selectable(
                        selected = selected,
                        interactionSource = interactionSource,
                        indication = ripple(),
                        enabled = enabled,
                        onClick = onClick
                    ),
            propagateMinConstraints = false,
            contentAlignment = contentAlignment,
            content = content,
        )
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
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier =
                modifier
                    .surface(
                        shape = shape,
                        backgroundColor = color,
                        border = border,
                        shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() }
                    )
                    .toggleable(
                        value = checked,
                        interactionSource = interactionSource,
                        indication = ripple(),
                        enabled = enabled,
                        onValueChange = onCheckedChange
                    ),
            propagateMinConstraints = false,
            contentAlignment = contentAlignment,
            content = content,
        )
    }
}

@Stable
private fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke?,
    shadowElevation: Float,
) = this
    .then(
        if (shadowElevation > 0f) {
            Modifier.graphicsLayer(
                shadowElevation = shadowElevation,
                shape = shape,
                clip = false,
            )
        } else {
            Modifier
        }
    )
    .then(if (border != null) Modifier.border(border, shape) else Modifier)
    .background(color = backgroundColor, shape = shape)
    .clip(shape)