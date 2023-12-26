package br.pedroso.jumblie.game

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.R
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnDeselectAll
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnIncorrectGuesses
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnLetter
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnPause
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnShuffle
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnSubmit
import br.pedroso.jumblie.ui.components.GameBoard
import br.pedroso.jumblie.ui.components.GameTheme
import br.pedroso.jumblie.ui.components.GameTimer
import br.pedroso.jumblie.ui.components.TimerState
import br.pedroso.jumblie.ui.components.WordsRow
import br.pedroso.jumblie.ui.theme.JumblieTheme

@Composable
fun GameScreen(
    viewModel: GameScreenViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()

    GameScreenUi(
        uiState,
        modifier = modifier,
        onUiEventCallback = viewModel::onUiEvent,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GameScreenUi(
    state: GameScreenUiState,
    modifier: Modifier = Modifier,
    onUiEventCallback: (event: GameScreenUiEvent) -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(8.dp)
    ) {
        GameTimer(
            modifier = Modifier.fillMaxWidth(),
            state = TimerState.Running,
            elapsedTime = 50L,
            onTimerButtonClick = { onUiEventCallback(ClickedOnPause) },
        )

        GameTheme(modifier = Modifier, theme = state.theme)

        GameBoard(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            letters = state.letters,
            selectedIndexes = state.selectedIndexes,
            onLetterClicked = { index -> onUiEventCallback(ClickedOnLetter(index)) },
            onShuffleClicked = { onUiEventCallback(ClickedOnShuffle) },
            onDeselectAllClicked = { onUiEventCallback(ClickedOnDeselectAll) },
            onSubmitClick = { onUiEventCallback(ClickedOnSubmit) },
        )

        if (state.correctGuesses.isNotEmpty()) {
            WordsRow(words = state.correctGuesses, modifier = Modifier.fillMaxWidth())
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = stringResource(id = R.string.no_correct_guesses),
                textAlign = TextAlign.Center
            )
        }

        OutlinedButton(
            onClick = { onUiEventCallback(ClickedOnIncorrectGuesses) },
            enabled = state.incorrectGuessesCount > 0
        ) {
            Text(
                text = stringResource(
                    id = R.string.incorrect_guesses,
                    state.incorrectGuessesCount
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun GameScreenPreview() {
    JumblieTheme {
        GameScreenUi(
            state = GameScreenUiState(
                theme = "Test",
                letters = GameScreenViewModel.TEST_WORDS.flatMap { it.toCharArray().toList() },
                selectedIndexes = emptyList(),
                correctGuesses = emptyList(),
                incorrectGuessesCount = 0,
            ),
        )
    }
}
