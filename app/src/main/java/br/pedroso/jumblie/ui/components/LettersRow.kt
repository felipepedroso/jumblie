package br.pedroso.jumblie.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.ui.theme.JumblieTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LettersRow(
    letters: List<Char>,
    selectedIndexes: List<Int>,
    modifier: Modifier = Modifier,
    onLetterClicked: (index: Int) -> Unit = {},
    maxLettersInEachRow: Int = Int.MAX_VALUE,
) {
    require(letters.all { it.isLetter() }) { "This Composable only accept letters!" }

    FlowRow(
        modifier = modifier,
        horizontalArrangement = spacedBy(4.dp, alignment = Alignment.CenterHorizontally),
        verticalArrangement = spacedBy(4.dp),
        maxItemsInEachRow = maxLettersInEachRow
    ) {
        letters.forEachIndexed { index, letter ->
            Letter(
                letterChar = letter,
                selected = index in selectedIndexes,
                onLetterClick = { onLetterClicked(index) }
            )
        }
    }
}

@Preview
@Composable
fun LettersRowPreview() {
    JumblieTheme {
        var selectedIndexes: List<Int> by remember {
            mutableStateOf(emptyList())
        }

        LettersRow(
            letters = listOf("Testing", "Letters").flatMap { it.toCharArray().asList() },
            selectedIndexes = selectedIndexes,
            maxLettersInEachRow = 5,
            onLetterClicked = { index ->
                selectedIndexes = if (index in selectedIndexes) {
                    selectedIndexes.filter { it != index }
                } else {
                    selectedIndexes + index
                }
            }
        )
    }
}
