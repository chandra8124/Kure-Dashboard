package org.flexi.app.presentation.ui.navigation.tabs.main

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.flexi.app.AppContent

class MainScreen(val mobileNumber: String?) : Screen {
    @Composable
    override fun Content() {
        AppContent()
        println("User Phone: $mobileNumber")
    }
}