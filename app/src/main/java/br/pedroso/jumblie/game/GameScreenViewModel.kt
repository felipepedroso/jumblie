package br.pedroso.jumblie.game

import androidx.lifecycle.ViewModel
import br.pedroso.jumblie.entities.Word
import br.pedroso.jumblie.entities.WordColor
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnCorrectGuesses
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnDeselectAll
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnIncorrectGuesses
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnLetter
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnPause
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnShuffle
import br.pedroso.jumblie.game.GameScreenUiEvent.ClickedOnSubmit
import br.pedroso.jumblie.utils.lettersToWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<GameScreenUiState> = MutableStateFlow(
        GameScreenUiState(
            theme = "Saints from UK countries",
            letters = TEST_WORDS.flatMap { it.toCharArray().toList() }.shuffled(),
            selectedIndexes = emptyList(),
            incorrectGuessesCount = 0,
            correctGuesses = emptyList(),
        )
    )

    val uiState: StateFlow<GameScreenUiState> = _uiState

    fun onUiEvent(uiEvent: GameScreenUiEvent) {
        when (uiEvent) {
            ClickedOnPause -> Unit
            ClickedOnDeselectAll -> clearLetterSelection()
            ClickedOnIncorrectGuesses -> Unit
            is ClickedOnLetter -> handleClickedOnLetter(uiEvent.letterIndex)
            ClickedOnShuffle -> shuffleLetters()
            ClickedOnSubmit -> submitWord()
            ClickedOnCorrectGuesses -> Unit
        }
    }

    private fun submitWord() {
        _uiState.update { currentState ->
            val word = lettersToWord(currentState.letters, currentState.selectedIndexes)

            val words = TEST_WORDS.map { it.lowercase() }.sortedBy { it.length }

            val wordIndex = words.indexOf(word)

            if (wordIndex != -1) {
                currentState.copy(
                    correctGuesses =
                        currentState.correctGuesses + Word(
                            word,
                            WordColor.entries[wordIndex]
                        ),
                    letters = currentState.letters.filterIndexed { index, _ ->
                        index !in currentState.selectedIndexes
                    },
                    selectedIndexes = emptyList()
                )
            } else {
                currentState.copy(
                    incorrectGuessesCount = currentState.incorrectGuessesCount + 1,
                    selectedIndexes = emptyList()
                )
            }
        }
    }

    private fun handleClickedOnLetter(letterIndex: Int) {
        _uiState.update { currentState ->
            val selectedIndexes = currentState.selectedIndexes

            val newSelectedIndexes = if (letterIndex in selectedIndexes) {
                selectedIndexes.filter { it != letterIndex }
            } else {
                selectedIndexes + letterIndex
            }

            currentState.copy(selectedIndexes = newSelectedIndexes)
        }
    }

    private fun shuffleLetters() {
        _uiState.update { currentState ->
            currentState.copy(
                selectedIndexes = emptyList(),
                letters = currentState.letters.shuffled()
            )
        }
    }

    private fun clearLetterSelection() {
        _uiState.update { currentState ->
            currentState.copy(selectedIndexes = emptyList())
        }
    }

    companion object {
        internal val TEST_WORDS = listOf("George", "Andrew", "David", "Patrick")
    }
}