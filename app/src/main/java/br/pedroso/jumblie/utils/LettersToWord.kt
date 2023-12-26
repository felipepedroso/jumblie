package br.pedroso.jumblie.utils

fun lettersToWords(letters: List<Char>, selectedIndexes: List<Int>): String {
    return selectedIndexes.joinToString("") { letters[it].lowercase() }
}
