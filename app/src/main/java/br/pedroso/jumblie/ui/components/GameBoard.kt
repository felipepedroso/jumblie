package br.pedroso.jumblie.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.pedroso.jumblie.ui.theme.JumblieTheme
import br.pedroso.jumblie.utils.lettersToWord

@Composable
fun GameBoard(
    letters: List<Char>,
    selectedIndexes: List<Int>,
    modifier: Modifier = Modifier,
    maxLettersInEachRow: Int = Int.MAX_VALUE,
    onLetterClicked: (index: Int) -> Unit = {},
    onSubmitClick: () -> Unit = {},
    onShuffleClicked: () -> Unit = {},
    onDeselectAllClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildString {
                append(lettersToWord(letters, selectedIndexes))
                if(isEmpty()) {
                    append("|")
                }
            },
            style = MaterialTheme.typography.displaySmall
        )

        LettersRow(
            letters = letters,
            selectedIndexes = selectedIndexes,
            onLetterClicked = onLetterClicked,
            maxLettersInEachRow = maxLettersInEachRow,
        )

        GameButtonsRow(
            modifier = Modifier.fillMaxWidth(),
            onShuffleClicked = onShuffleClicked,
            onDeselectAllClicked = onDeselectAllClicked,
            onSubmitClick = onSubmitClick,
            submitButtonEnabled = selectedIndexes.isNotEmpty()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameBoardPreview() {
    JumblieTheme {
        GameBoard(
            letters = listOf("Testing", "Game", "Board").flatMap {
                it.toCharArray().asList()
            },
            selectedIndexes = listOf(0, 6, 4, 12),
        )
    }
}
