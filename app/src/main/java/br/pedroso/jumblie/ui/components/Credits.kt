package br.pedroso.jumblie.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.pedroso.jumblie.R
import br.pedroso.jumblie.ui.theme.JumblieTheme

@Composable
fun Credits(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(16.dp),
        text = buildAnnotatedString {
            append(stringResource(id = R.string.app_name))
            appendInlineContent(id = "imageId")
            append(" ")
            append(stringResource(id = R.string.credits_phrase))
        },
        style = MaterialTheme.typography.labelLarge,
        textAlign = TextAlign.Center,
        inlineContent = mapOf(
            "imageId" to InlineTextContent(
                Placeholder(7.sp, 7.sp, PlaceholderVerticalAlign.TextTop)
            ) {
                JumblieIcon()
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CreditsPreview() {
    JumblieTheme {
        Credits()
    }
}
