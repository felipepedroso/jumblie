package br.pedroso.jumblie.incorrectguesses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.R

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun IncorrectGuesses(
    incorrectGuesses: List<String>,
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit = {},
) {
    Surface(modifier) {
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .padding(16.dp),
            verticalArrangement = Arrangement.Absolute.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.incorrect_guesses, incorrectGuesses.size),
                style = MaterialTheme.typography.titleMedium
            )

            FlowRow() {
                incorrectGuesses.forEach { IncorrectGuess(text = it) }
            }

            Button(onClick = onDismissClick) {
                Text(text = stringResource(id = R.string.got_it))
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
private fun IncorrectGuessesPreview() {
    IncorrectGuesses(
        incorrectGuesses = listOf("asdf", "qwer", "zxcv", "asdf", "qwer", "zxcv"),
    )
}
