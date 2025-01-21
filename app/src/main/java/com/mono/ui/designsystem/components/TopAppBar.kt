@file:OptIn(ExperimentalMaterial3Api::class)

package com.mono.ui.designsystem.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mono.ui.MonoIconButton
import com.mono.ui.designsystem.icons.PreviewIcons

@Composable
fun PreviewTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onNavigate: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            if (title != null) {
                Text(text = title,)
            }
        },
        navigationIcon = {
            if (onNavigate != null) {
                MonoIconButton(
                    icon = PreviewIcons.ArrowBack,
                    onClick = onNavigate
                )
            }
        },
        actions = actions ?: {},
    )
}