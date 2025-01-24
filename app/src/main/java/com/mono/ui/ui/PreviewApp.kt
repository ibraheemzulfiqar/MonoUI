package com.mono.ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mono.ui.BottomSheetDismissibleState
import com.mono.ui.ButtonConstraints
import com.mono.ui.MobileUiProfile
import com.mono.ui.MonoBottomSheetDialog
import com.mono.ui.MonoButton
import com.mono.ui.MonoButtonDefaults
import com.mono.ui.MonoFloatingActionButton
import com.mono.ui.ProvideUiProfile
import com.mono.ui.UiProfile
import com.mono.ui.designsystem.icons.PreviewIcons
import com.mono.ui.designsystem.theme.MonoUITheme
import com.mono.ui.rememberBottomSheetDismissibleState

@Composable
fun PreviewApp() {
    val navController = rememberNavController()
    val customizationDialog = rememberBottomSheetDismissibleState()

    var uiProfile: UiProfile by remember { mutableStateOf(MobileUiProfile()) }

    MonoUITheme {
        ProvideUiProfile(uiProfile) {
            CustomizationBottomSheet(
                state = customizationDialog,
                currentProfile = uiProfile,
                onUpdateProfile = { uiProfile = it }
            )

            Scaffold(
                floatingActionButton = {
                    MonoFloatingActionButton(
                        icon = PreviewIcons.Customize,
                        constraints = MonoButtonDefaults.largeFabConstraints(),
                        onClick = { customizationDialog.show() },
                    )
                }
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = HomeScreen
                ) {
                    homeScreenRoute(navController)
                    buttonPreviewScreenRoute(navController)
                    dialogPreviewScreenRoute(navController)
                    textFieldPreviewScreenRoute(navController)
                }
            }
        }
    }
}

@Composable
fun CustomizationBottomSheet(
    state: BottomSheetDismissibleState,
    currentProfile: UiProfile,
    onUpdateProfile: (UiProfile) -> Unit,
) {
    @Composable
    fun SelectableButton(
        text: String,
        isSelected: Boolean,
        onClick: () -> Unit,
    ) {
        MonoButton(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            colors = if (isSelected) ButtonDefaults.buttonColors() else ButtonDefaults.outlinedButtonColors(),
            border = if (isSelected) null else MonoButtonDefaults.outlinedBorder(),
            onClick = onClick,
        )
    }

    MonoBottomSheetDialog(
        state = state,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Select App Profile",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            SelectableButton(
                text = "Mobile Profile",
                isSelected = currentProfile::class == MobileUiProfile::class,
                onClick = {
                    onUpdateProfile(MobileUiProfile())
                }
            )

            SelectableButton(
                text = "Mobile Small Corner Profile",
                isSelected = currentProfile is MobileSmallCornerProfile,
                onClick = {
                    onUpdateProfile(MobileSmallCornerProfile)
                }
            )

            SelectableButton(
                text = "Tablet Profile",
                isSelected = currentProfile is TabletProfile,
                onClick = {
                    onUpdateProfile(TabletProfile)
                }
            )
        }
    }
}


object MobileSmallCornerProfile : MobileUiProfile() {
    override val buttonShape: Shape = RoundedCornerShape(4.dp)
}

object TabletProfile : MobileUiProfile() {
    override val buttonConstraints: ButtonConstraints = MonoButtonDefaults.constraints(
        minHeight = 54.dp,
        minWidth = 74.dp,
        iconSize = 24.dp
    )
    override val buttonTextStyle: TextStyle = MonoButtonDefaults.textStyle.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
    )
}

