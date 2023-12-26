package br.pedroso.jumblie.helpdialog

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.R
import br.pedroso.jumblie.ui.theme.JumblieTheme

@Composable
fun HelpDialog(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit = {},
) {
    Surface(modifier) {
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .padding(16.dp),
            verticalArrangement = spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.how_to_play_jumblie_title),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = stringArrayResource(id = R.array.how_to_play_jumblie).joinToString("\n\n"),
                style = MaterialTheme.typography.bodyMedium
            )

            Button(onClick = onDismissClick) {
                Text(text = stringResource(id = R.string.got_it))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HelpDialogPreview() {
    JumblieTheme {
        HelpDialog(onDismissClick = {})
    }
}