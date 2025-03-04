package org.flexi.app.presentation.ui.navigation.tabs.orders

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object MyOrders: Tab {
    @Composable
    override fun Content() {
       // Navigator(MyOrdersContent())
    }

    override val options: TabOptions
        @Composable
        get() {
            val title by remember { mutableStateOf("My Order") }
            val icon = rememberVectorPainter(Icons.Default.DeliveryDining)
            val index: UShort = 1u
            return TabOptions(index, title, icon)
        }
}