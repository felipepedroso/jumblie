package br.pedroso.jumblie.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.R
import br.pedroso.jumblie.utils.applyModifierWhen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IncorrectGuesses(
    incorrectGuesses: List<String>,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onExpandClick: () -> Unit = {},
    guessesScrollState: ScrollState = rememberScrollState(),
) {
    Column(modifier = modifier, verticalArrangement = spacedBy(4.dp)) {
        Row(
            modifier = Modifier
                .applyModifierWhen(incorrectGuesses.isNotEmpty()) {
                    clickable(onClick = onExpandClick)
                }
                .minimumInteractiveComponentSize(),
            horizontalArrangement = spacedBy(8.dp)
        ) {

            Icon(
                painter = painterResource(
                    id = if (expanded) {
                        R.drawable.ic_arrow_down
                    } else {
                        R.drawable.ic_arrow_right
                    }
                ),
                contentDescription = null,
            )

            Text(
                text = stringResource(id = R.string.incorrect_guesses, incorrectGuesses.size),
                style = MaterialTheme.typography.titleMedium
            )
        }

        AnimatedVisibility(visible = expanded && incorrectGuesses.isNotEmpty()) {
            Row(modifier = Modifier.horizontalScroll(guessesScrollState)) {
                incorrectGuesses.forEach { IncorrectGuess(text = it) }
            }
        }
    }
}

@Composable
fun IncorrectGuess(text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(8.dp), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun IncorrectGuessesPreview(
    @PreviewParameter(GuessesParameterProvider::class) guesses: List<String>
) {
    var expanded: Boolean by remember {
        mutableStateOf(false)
    }

    IncorrectGuesses(
        incorrectGuesses = guesses,
        expanded = expanded,
        onExpandClick = { expanded = !expanded }
    )
}

private class GuessesParameterProvider : PreviewParameterProvider<List<String>> {
    override val values: Sequence<List<String>>
        get() = sequenceOf(emptyList(), listOf("asdf", "qwer", "zxcv", "asdf", "qwer", "zxcv"))
}