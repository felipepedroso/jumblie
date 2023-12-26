package br.pedroso.jumblie.startscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.R
import br.pedroso.jumblie.ui.theme.JumblieTheme

@Composable
fun StartScreen(
    viewModel: StartScreenViewModel,
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit = {},
) {
    val theme by viewModel.theme.collectAsState()

    StartScreenUi(theme, modifier, onStartClick)
}

@Composable
private fun StartScreenUi(
    theme: String,
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit = {},
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Absolute.spacedBy(
            8.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = stringResource(id = R.string.todays_theme),
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = theme,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Button(onClick = onStartClick) {
            Text(text = stringResource(id = R.string.start))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StartScreenPreview() {
    JumblieTheme {
        StartScreenUi(theme = "Test", modifier = Modifier.fillMaxSize())
    }
}