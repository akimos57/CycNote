package ru.cyclone.cycnote.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.cyclone.cycnote.presentation.navigation.SetupNavHost
import ru.cyclone.cycnote.presentation.ui.theme.CycNoteTheme
import ru.cyclone.cycnote.presentation.ui.theme.backgroundLightColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CycNoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundLightColor
                ) {
                    SetupNavHost(navController = navController)
                }
            }
        }
    }
}
