package br.pedroso.jumblie.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import br.pedroso.jumblie.R
import br.pedroso.jumblie.ui.theme.JumblieTheme
import br.pedroso.jumblie.utils.formatElapsedTime

enum class TimerState {
    Running, Paused,
}

@Composable
fun GameTimer(
    elapsedTime: Long,
    state: TimerState,
    modifier: Modifier = Modifier,
    onTimerButtonClick: () -> Unit = {},
) {
    val icon = if (state == TimerState.Running) {
        R.drawable.ic_pause
    } else {
        R.drawable.ic_play
    }

    TextButton(modifier = modifier, onClick = onTimerButtonClick) {
        Icon(painterResource(id = icon), contentDescription = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = formatElapsedTime(elapsedTime))
    }
}

@Preview(showBackground = true)
@Composable
private fun GameTimerPreview(@PreviewParameter(TimerStateParameterProvider::class) timerState: TimerState) {
    JumblieTheme {
        GameTimer(state = timerState, elapsedTime = 50L)
    }
}

private class TimerStateParameterProvider : PreviewParameterProvider<TimerState> {
    override val values: Sequence<TimerState>
        get() = TimerState.entries.asSequence()
}
