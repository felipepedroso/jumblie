package br.pedroso.jumblie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.pedroso.jumblie.home.HomeScreen
import br.pedroso.jumblie.ui.theme.JumblieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JumblieTheme {
                HomeScreen()
            }
        }
    }
}

