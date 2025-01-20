package com.mono.ui.designsystem.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.DashboardCustomize
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import com.mono.ui.MonoIcon
import com.mono.ui.getValue
import com.mono.ui.rememberMonoIcon

object MonoIcons {

    @get:Composable
    val Call by Icons.Outlined.Call

    @get:Composable
    val Share by Icons.Outlined.Share

    @get:Composable
    val Delete by Icons.Outlined.DeleteOutline

    @get:Composable
    val Customize by Icons.Outlined.DashboardCustomize

    val Copy: MonoIcon
        @Composable
        get() = rememberMonoIcon(
            imageVector = Icons.Outlined.ContentCopy,
            description = "Copy",
        )

}