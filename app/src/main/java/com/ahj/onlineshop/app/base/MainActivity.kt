package com.ahj.onlineshop.app.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ahj.onlineshop.app.navigation.SetupUI
import com.ahj.onlineshop.core.common.ui.theme.OnlineShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            OnlineShopTheme {
                SetupUI()
            }
        }
    }
}