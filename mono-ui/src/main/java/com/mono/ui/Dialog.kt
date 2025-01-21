@file:OptIn(ExperimentalMaterial3Api::class)

package com.mono.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.mono.ui.internal.ProvideContentColorTextStyle

// TODO: Local Composition for dialogs including colors, radius, outline buttons primary & secondary, probably text style and spacing too
// only if needed

@Composable
fun MonoAlertDialog(
    state: DismissibleState,
    modifier: Modifier = Modifier,
    title: String? = null,
    text: String? = null,
    icon: @Composable (() -> Unit)? = null,
    primaryButton: String? = null,
    onPrimaryButtonClick: (() -> Unit)? = null,
    secondaryButton: String? = null,
    onSecondaryButtonClick: (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    colors: MonoDialogDefaults.DialogColors = MonoDialogDefaults.colors(),
) {
    MonoAlertDialog(
        state = state,
        modifier = modifier,
        title = title,
        text = text,
        icon = icon,
        properties = properties,
        colors = colors
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            if (secondaryButton != null && onSecondaryButtonClick != null) {
                MonoButton(
                    modifier = Modifier.weight(1f),
                    text = secondaryButton,
                    colors = colors.secondaryButton,
                    onClick = onSecondaryButtonClick,
                )
            }

            if (primaryButton != null && onPrimaryButtonClick != null) {
                MonoButton(
                    modifier = Modifier.weight(1f),
                    text = primaryButton,
                    colors = colors.primaryButton,
                    onClick = onPrimaryButtonClick,
                )
            }
        }
    }
}

@Composable
fun MonoAlertDialog(
    state: DismissibleState,
    modifier: Modifier = Modifier,
    title: String? = null,
    text: String? = null,
    titleStyle: TextStyle = typography.headlineSmall,
    textStyle: TextStyle = typography.bodyMedium,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    colors: MonoDialogDefaults.DialogColors = MonoDialogDefaults.colors(),
    icon: @Composable (() -> Unit)? = null,
    buttons: @Composable (() -> Unit)? = null,
) {
    MonoDialog(
        state = state,
        modifier = modifier,
        properties = properties,
        color = colors.container,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (icon != null) {
            CompositionLocalProvider(
                LocalContentColor provides colors.icon
            ) {
                Box(
                    Modifier
                        .padding(IconPadding)
                        .align(Alignment.CenterHorizontally)
                ) {
                    icon()
                }
            }
        }

        if (title != null) {
            ProvideContentColorTextStyle(
                contentColor = colors.title,
                textStyle = titleStyle
            ) {
                Box(
                    // Align the title to the center when an icon is present.
                    Modifier
                        .padding(TitlePadding)
                        .align(
                            if (icon == null) {
                                Alignment.Start
                            } else {
                                Alignment.CenterHorizontally
                            }
                        )
                ) {
                    Text(text = title)
                }
            }
        }

        if (text != null) {
            ProvideContentColorTextStyle(
                contentColor = colors.text,
                textStyle = textStyle
            ) {
                Box(
                    Modifier
                        .weight(weight = 1f, fill = false)
                        .padding(TextPadding)
                        .align(Alignment.Start)
                ) {
                    Text(text = text)
                }
            }
        }

        if (buttons != null) {
            Box(modifier = Modifier.align(Alignment.End)) {
                val textStyle = typography.labelLarge

                ProvideContentColorTextStyle(
                    contentColor = LocalContentColor.current,
                    textStyle = textStyle,
                    content = buttons,
                )
            }
        }
    }
}


@Composable
fun MonoDialog(
    state: DismissibleState,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    shape: Shape = RoundedCornerShape(24.dp),
    contentPadding: PaddingValues = DialogPadding,
    color: Color = colorScheme.surfaceContainerHigh,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    if (state.dismissed) return

    BasicAlertDialog(
        modifier = modifier,
        properties = properties,
        onDismissRequest = state::dismiss,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .clip(shape)
                .background(color)
                .padding(contentPadding),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content,
        )
    }
}

object MonoDialogDefaults {

    @Composable
    fun colors(
        container: Color = colorScheme.surfaceContainerHigh,
        icon: Color = colorScheme.secondary,
        title: Color = colorScheme.onSurface,
        text: Color = colorScheme.onSurfaceVariant,
        primaryButton: ButtonColors = ButtonDefaults.buttonColors(),
        secondaryButton: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    ) = DialogColors(
        container = container,
        icon = icon,
        title = title,
        text = text,
        primaryButton = primaryButton,
        secondaryButton = secondaryButton,
    )

    data class DialogColors(
        val container: Color,
        val icon: Color,
        val title: Color,
        val text: Color,
        val primaryButton: ButtonColors,
        val secondaryButton: ButtonColors,
    )

}

// Paddings for each of the dialog's parts.
private val DialogPadding = PaddingValues(all = 24.dp)
private val IconPadding = PaddingValues(bottom = 16.dp)
private val TitlePadding = PaddingValues(bottom = 16.dp)
private val TextPadding = PaddingValues(bottom = 24.dp)