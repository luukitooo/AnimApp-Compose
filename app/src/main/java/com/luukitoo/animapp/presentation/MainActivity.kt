package com.luukitoo.animapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.luukitoo.animapp.presentation.navigation.MainNavHost
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AnimAppTheme { Surface { MainNavHost() } } }
    }

    @Composable
    @SuppressLint("ComposableNaming")
    private fun setSystemBarsTransparent() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        val uiController = rememberSystemUiController()
        uiController.setSystemBarsColor(color = Color.Transparent)
    }
}