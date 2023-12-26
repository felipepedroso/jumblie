package br.pedroso.jumblie

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.ui.components.GameBoard
import br.pedroso.jumblie.ui.components.GameButtonsRow
import br.pedroso.jumblie.ui.components.GameTheme
import br.pedroso.jumblie.ui.components.GameTimer
import br.pedroso.jumblie.ui.components.IncorrectGuesses
import br.pedroso.jumblie.ui.components.TimerState
import br.pedroso.jumblie.ui.theme.JumblieTheme

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            GameTopAppBar(
                onHelpClicked = { /*TODO*/ },
                onSettingsClicked = { /*TODO*/ },
                onStatisticsClicked = { /*TODO*/ },
            )
        },
        bottomBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.credits_phrase))
                },
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameTheme(modifier = Modifier.weight(1f), theme = "Test")
                GameTimer(
                    modifier = Modifier.weight(1f),
                    state = TimerState.Running,
                    ellapsedTime = 50L
                )
            }

            GameBoard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                letters = listOf('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
                selectedIndexes = listOf(1, 5, 6, 7, 8)
            )

            GameButtonsRow(
                modifier = Modifier.fillMaxWidth(),
                onShuffleClick = { /*TODO*/ },
                onDeselectAllClick = { /*TODO*/ },
                onSubmitClick = { /*TODO*/ }
            )

            var expanded: Boolean by remember {
                mutableStateOf(false)
            }

            IncorrectGuesses(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                incorrectGuesses = listOf("asdf", "qwer", "zxcv", "asdf", "qwer", "zxcv", "zxcv", "zxcv"),
                onExpandClick = { expanded = !expanded }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameTopAppBar(
    modifier: Modifier = Modifier,
    onHelpClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onStatisticsClicked: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = onHelpClicked) {
                Icon(painterResource(id = R.drawable.ic_help), contentDescription = null)
            }
            IconButton(onClick = onSettingsClicked) {
                Icon(painterResource(id = R.drawable.ic_bar_chart), contentDescription = null)
            }
            IconButton(onClick = onStatisticsClicked) {
                Icon(painterResource(id = R.drawable.ic_settings), contentDescription = null)
            }
        },
    )
}

@Preview
@Composable
fun GameScreenPreview() {
    JumblieTheme {
        GameScreen()
    }
}
