package com.mono.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

val DismissibleState.dismissed: Boolean
    get() = visible.not()

@Composable
fun rememberDismissibleState(
    visible: Boolean = false,
    onStateChange: ((Boolean) -> Unit)? = null,
) : DismissibleState {
    return remember(Unit) { DismissibleState(visible, onStateChange) }
}

fun DismissibleState(
    visible: Boolean,
    onStateChange: ((Boolean) -> Unit)? = null,
): DismissibleState = DismissibleStateImpl(visible, onStateChange)

interface DismissibleState {

    val visible: Boolean

    fun show()

    fun dismiss()

}


private class DismissibleStateImpl(
    default: Boolean,
    private val listener: ((Boolean) -> Unit)? = null
) : DismissibleState {

    override var visible: Boolean by mutableStateOf(default)
        private set

    override fun show() {
        visible = true
        listener?.invoke(true)
    }

    override fun dismiss() {
        visible = false
        listener?.invoke(false)
    }

}