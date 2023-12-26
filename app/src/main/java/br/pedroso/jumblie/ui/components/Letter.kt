package br.pedroso.jumblie.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.ui.theme.JumblieTheme

@Composable
fun Letter(
    letterChar: Char,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    colors: LetterColors = LetterDefaults.colors,
    onLetterClick: () -> Unit = {}
) {
    require(letterChar.isLetter()) { "This Composable only accept letters!" }

    val transition = updateTransition(targetState = selected, label = "color-transition")

    val backgroundColor by transition.animateColor(label = "color-transition-background") {
        colors.background(it)
    }

    val textColor by transition.animateColor(label = "color-transition-background") {
        colors.text(it)
    }

    Box(
        modifier = modifier
            .selectable(selected, onClick = onLetterClick)
            .sizeIn(48.dp, 48.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letterChar.uppercase(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(700),
            style = MaterialTheme.typography.bodyLarge,
            color = textColor,
        )
    }
}

object LetterDefaults {
    val colors = LetterColors(
        backgroundDefault = Color(0xffefe9e6),
        backgroundSelected = Color(0xff5a534e),
        textDefault = Color(0xff000000),
        textSelected = Color(0xffffffff),
    )
}

class LetterColors(
    private val backgroundDefault: Color,
    private val backgroundSelected: Color,
    private val textDefault: Color,
    private val textSelected: Color
) {
    fun background(isSelected: Boolean): Color =
        if (isSelected) backgroundSelected else backgroundDefault

    fun text(isSelected: Boolean): Color =
        if (isSelected) textSelected else textDefault
}

@Preview
@Composable
fun LetterPreview() {
    JumblieTheme {
        var isSelected: Boolean by remember {
            mutableStateOf(false)
        }

        Letter(
            letterChar = 'a',
            selected = isSelected,
            onLetterClick = { isSelected = !isSelected }
        )
    }
}