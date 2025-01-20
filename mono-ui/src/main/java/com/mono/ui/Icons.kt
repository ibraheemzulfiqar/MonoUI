package com.mono.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import kotlin.reflect.KProperty

@Composable
fun MonoIcon(
    icon: MonoIcon,
    modifier: Modifier = Modifier,
    description: String? = null,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        painter = icon.painter,
        contentDescription = description ?: icon.description,
        modifier = modifier,
        tint = tint,
    )
}

@Composable
fun rememberMonoIcon(
    painter: Painter,
    description: String? = null,
): MonoIcon {
    return remember(painter, description) {
        MonoIcon(painter, description)
    }
}

@Composable
fun rememberMonoIcon(
    imageVector: ImageVector,
    @StringRes descriptionRes: Int? = null,
): MonoIcon {
    return rememberMonoIcon(
        imageVector = imageVector,
        description = descriptionRes?.let { stringResource(it) },
    )
}

@Composable
fun rememberMonoIcon(
    imageVector: ImageVector,
    description: String? = null,
): MonoIcon {
    return rememberMonoIcon(
        painter = rememberVectorPainter(imageVector),
        description = description,
    )
}

@Composable
fun rememberMonoIcon(
    @DrawableRes id: Int,
    @StringRes descriptionRes: Int? = null,
): MonoIcon {
    val a by remember { mutableStateOf("") }
    return rememberMonoIcon(
        id = id,
        description = descriptionRes?.let { stringResource(it) },
    )
}

@Composable
fun rememberMonoIcon(
    @DrawableRes id: Int,
    description: String? = null,
): MonoIcon {
    return rememberMonoIcon(
        painter = painterResource(id),
        description = description,
    )
}

@Composable
operator fun ImageVector.getValue(thisObj: Any?, property: KProperty<*>): MonoIcon {
    return rememberMonoIcon(this, description = null)
}

@Immutable
data class MonoIcon(
    val painter: Painter,
    val description: String? = null,
)