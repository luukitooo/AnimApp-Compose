package com.luukitoo.animapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.luukitoo.animapp.presentation.navigation.MainNavHost
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setComposeContent()
    }

    private fun setComposeContent() = setContent {
        AnimAppTheme {
            Surface {
                MainNavHost()
            }
        }
    }
}