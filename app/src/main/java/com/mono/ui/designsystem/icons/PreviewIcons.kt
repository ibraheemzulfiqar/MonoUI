package com.mono.ui.designsystem.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.DashboardCustomize
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import com.mono.ui.MonoIcon
import com.mono.ui.getValue
import com.mono.ui.rememberMonoIcon

object PreviewIcons {

    @get:Composable
    val Call by Icons.Outlined.Call

    @get:Composable
    val Share by Icons.Outlined.Share

    @get:Composable
    val Delete by Icons.Outlined.DeleteOutline

    @get:Composable
    val Customize by Icons.Outlined.DashboardCustomize

    @get:Composable
    val Menu by Icons.Default.MoreVert

    @get:Composable
    val Settings by Icons.Outlined.Settings

    @get:Composable
    val Feedback by Icons.Outlined.Feedback

    val Copy: MonoIcon
        @Composable
        get() = rememberMonoIcon(
            imageVector = Icons.Outlined.ContentCopy,
            description = "Copy",
        )

    val ArrowBack: MonoIcon
        @Composable
        get() = rememberMonoIcon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            description = "Navigate back",
        )

}