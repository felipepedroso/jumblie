package br.pedroso.jumblie.statistics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.pedroso.jumblie.ui.theme.JumblieTheme

@Composable
fun ArchiveScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "WIP")
    }
}


@Preview(showBackground = true)
@Composable
private fun ArchiveScreenPreview() {
    JumblieTheme {
        ArchiveScreen(modifier = Modifier.fillMaxSize())
    }
}