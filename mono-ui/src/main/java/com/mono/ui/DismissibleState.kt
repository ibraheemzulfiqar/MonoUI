package com.mono.ui

import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val DismissibleState.dismissed: Boolean
    get() = visible.not()

@Composable
fun rememberDismissibleState(
    visible: Boolean = false,
    onStateChange: ((Boolean) -> Unit)? = null,
): DismissibleState {
    return remember(Unit) { DismissibleState(visible, onStateChange) }
}


@Composable
fun rememberBottomSheetDismissibleState(
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    scope: CoroutineScope = rememberCoroutineScope(),
    onStateChange: ((Boolean) -> Unit)? = null,
): BottomSheetDismissibleState {
    return remember(Unit) {
        BottomSheetDismissibleState(
            sheetState = sheetState,
            scope = scope,
            listener = onStateChange
        )
    }
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

class BottomSheetDismissibleState(
    val sheetState: SheetState,
    private val scope: CoroutineScope,
    private val listener: ((Boolean) -> Unit)? = null
) : DismissibleState {

    override var visible: Boolean by mutableStateOf(sheetState.isVisible)
        private set

    override fun show() {
        scope.launch {
            visible = true
            sheetState.show()
            listener?.invoke(true)
        }
    }

    override fun dismiss() {
        scope.launch {
            sheetState.hide()
            visible = false
            listener?.invoke(false)
        }
    }
}