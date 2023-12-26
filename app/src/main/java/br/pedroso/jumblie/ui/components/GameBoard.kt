package br.pedroso.jumblie.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.utils.lettersToWords

@Composable
fun GameBoard(
    letters: List<Char>,
    selectedIndexes: List<Int>,
    modifier: Modifier = Modifier,
    maxLettersInEachRow: Int = Int.MAX_VALUE,
    onLetterClicked: (index: Int) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = lettersToWords(letters, selectedIndexes),
            style = MaterialTheme.typography.bodyLarge
        )

        LettersRow(
            letters = letters,
            selectedIndexes = selectedIndexes,
            onLetterClicked = onLetterClicked,
            maxLettersInEachRow = maxLettersInEachRow,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameBoardPreview() {
    GameBoard(
        letters = listOf("Testing", "Game", "Board").flatMap {
            it.toCharArray().asList()
        },
        selectedIndexes = listOf(),
    )
}
