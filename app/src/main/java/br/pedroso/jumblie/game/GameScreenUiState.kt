package br.pedroso.jumblie.game

import br.pedroso.jumblie.entities.Word

data class GameScreenUiState(
    val theme: String,
    val letters: List<Char>,
    val selectedIndexes: List<Int>,
    val incorrectGuessesCount: Int,
    val correctGuesses: List<Word>
)


