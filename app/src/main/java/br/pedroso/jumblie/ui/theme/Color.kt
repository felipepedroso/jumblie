package br.pedroso.jumblie.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

class JumblieColorScheme(
    val redWord: Color,
    val orangeWord: Color,
    val greenWord: Color,
    val blueWord: Color,
)

fun lightJumblieColorScheme() = JumblieColorScheme(
    redWord = Color(0xffEF5350),
    orangeWord = Color(0xffFFA726),
    greenWord = Color(0xff66BB6A),
    blueWord = Color(0xff42A5F5),
)

fun darkJumblieColorScheme() = JumblieColorScheme(
    redWord = Color(0xffC62828),
    orangeWord = Color(0xffEF6C00),
    greenWord = Color(0xff2E7D32),
    blueWord = Color(0xff1565C0),
)

internal val LocalJumblieColorScheme = staticCompositionLocalOf { lightJumblieColorScheme() }