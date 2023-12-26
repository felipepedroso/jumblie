package br.pedroso.jumblie.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.entities.Word
import br.pedroso.jumblie.entities.WordColor
import br.pedroso.jumblie.ui.theme.JumblieTheme
import br.pedroso.jumblie.utils.toThemeColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordsRow(
    words: List<Word>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = spacedBy(8.dp),
        horizontalArrangement = spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
    ) {
        words.forEach {
            WordItem(
                word = it.text,
                color = it.color.toThemeColor(),
                showBorder = it.color == WordColor.Incorrect
            )
        }
    }
}

@Composable
private fun WordItem(
    word: String,
    color: Color,
    modifier: Modifier = Modifier,
    showBorder: Boolean = false,
) {
    Surface(
        modifier = modifier,
        color = color,
        shape = CircleShape,
        border = if (showBorder) BorderStroke(1.dp, MaterialTheme.colorScheme.outline) else null
    ) {
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = word, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WordsRowPreview(@PreviewParameter(WordsParameterProvider::class) words: List<Word>) {
    JumblieTheme {
        Surface {
            WordsRow(words)
        }

    }
}

private class WordsParameterProvider : PreviewParameterProvider<List<Word>> {
    override val values: Sequence<List<Word>>
        get() = sequenceOf(
            listOf(
                Word("George", WordColor.Orange),
                Word("Andrew", WordColor.Green),
                Word("Patrick", WordColor.Blue),
                Word("David", WordColor.Red)
            ),

            listOf(
                Word("These", WordColor.Incorrect),
                Word("Words", WordColor.Incorrect),
                Word("Are", WordColor.Incorrect),
                Word("Incorrect", WordColor.Incorrect)
            ),
        )
}