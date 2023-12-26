package br.pedroso.jumblie.game

sealed class GameScreenUiEvent {
    data object ClickedOnPause : GameScreenUiEvent()
    data object ClickedOnSubmit : GameScreenUiEvent()
    data object ClickedOnCorrectGuesses : GameScreenUiEvent()
    data object ClickedOnIncorrectGuesses : GameScreenUiEvent()
    data object ClickedOnShuffle : GameScreenUiEvent()
    data object ClickedOnDeselectAll : GameScreenUiEvent()
    data class ClickedOnLetter(val letterIndex: Int) : GameScreenUiEvent()
}
