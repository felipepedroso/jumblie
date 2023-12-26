package br.pedroso.jumblie.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.R

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun GameButtonsRow(
    modifier: Modifier = Modifier,
    onSubmitClick: () -> Unit = {},
    onShuffleClicked: () -> Unit = {},
    onDeselectAllClicked: () -> Unit = {},
    submitButtonEnabled: Boolean = true,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        OutlinedButton(onClick = onShuffleClicked) {
            Text(text = stringResource(id = R.string.shuffle))
        }

        OutlinedButton(onClick = onDeselectAllClicked) {
            Text(text = stringResource(id = R.string.deselect_all))
        }

        Button(onClick = onSubmitClick, enabled = submitButtonEnabled) {
            Text(text = stringResource(id = R.string.submit))
        }
    }
}