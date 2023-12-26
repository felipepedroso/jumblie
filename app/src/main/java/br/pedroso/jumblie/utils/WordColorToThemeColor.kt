package br.pedroso.jumblie.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.pedroso.jumblie.entities.WordColor
import br.pedroso.jumblie.entities.WordColor.Blue
import br.pedroso.jumblie.entities.WordColor.Green
import br.pedroso.jumblie.entities.WordColor.Incorrect
import br.pedroso.jumblie.entities.WordColor.Orange
import br.pedroso.jumblie.entities.WordColor.Red
import br.pedroso.jumblie.ui.theme.JumblieTheme

@Composable
fun WordColor.toThemeColor(): Color {
    return when (this) {
        Red -> JumblieTheme.colorScheme.redWord
        Orange -> JumblieTheme.colorScheme.orangeWord
        Green -> JumblieTheme.colorScheme.greenWord
        Blue -> JumblieTheme.colorScheme.blueWord
        Incorrect -> Color.Transparent
    }
}
