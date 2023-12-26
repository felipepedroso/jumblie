package br.pedroso.jumblie.ui.components

import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.pedroso.jumblie.R

@Composable
fun JumblieTitle(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.app_name))
                appendInlineContent(id = "imageId")
            },
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            inlineContent = mapOf(
                "imageId" to InlineTextContent(
                    Placeholder(16.sp, 16.sp, PlaceholderVerticalAlign.TextTop)
                ) {
                    JumblieIcon()
                }
            )
        )
    }
}

@Preview
@Composable
private fun LogoPreview() {
    JumblieTitle()
}