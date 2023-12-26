package br.pedroso.jumblie

enum class Screens(val showNavigationArrow: Boolean = false) {
    Game,
    Help,
    Start,
    Settings(showNavigationArrow = true),
    Statistics(showNavigationArrow = true),
    Archive(showNavigationArrow = true);
}
