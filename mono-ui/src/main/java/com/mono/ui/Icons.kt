package com.mono.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun MonoIcon(
    icon: MonoIcon,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        painter = icon.painter,
        contentDescription = contentDescription ?: icon.contentDescription,
        modifier = modifier,
        tint = tint,
    )
}

@Composable
fun rememberMonoIcon(
    painter: Painter,
    contentDescription: String? = null,
): MonoIcon {
    return remember(painter, contentDescription) {
        MonoIcon(painter, contentDescription)
    }
}

@Composable
fun rememberMonoIcon(
    imageVector: ImageVector,
    @StringRes contentDescription: Int? = null,
): MonoIcon {
    return rememberMonoIcon(
        imageVector = imageVector,
        contentDescription = contentDescription?.let { stringResource(it) },
    )
}

@Composable
fun rememberMonoIcon(
    imageVector: ImageVector,
    contentDescription: String? = null,
): MonoIcon {
    return rememberMonoIcon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
    )
}

@Composable
fun rememberMonoIcon(
    @DrawableRes id: Int,
    @StringRes contentDescription: Int? = null,
): MonoIcon {
    return rememberMonoIcon(
        id = id,
        contentDescription = contentDescription?.let { stringResource(it) },
    )
}

@Composable
fun rememberMonoIcon(
    @DrawableRes id: Int,
    contentDescription: String? = null,
): MonoIcon {
    return rememberMonoIcon(
        painter = painterResource(id),
        contentDescription = contentDescription,
    )
}

@Immutable
data class MonoIcon(
    val painter: Painter,
    val contentDescription: String? = null,
)