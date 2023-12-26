package br.pedroso.jumblie.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.pedroso.jumblie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopAppBar(
    modifier: Modifier = Modifier,
    showNavigationArrow: Boolean = false,
    onHelpClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onArchiveClick: () -> Unit,
    onNavigationIconClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { JumblieTitle() },
        navigationIcon = {
            AnimatedVisibility(
                visible = showNavigationArrow,
                enter = expandHorizontally(),
                exit = shrinkHorizontally(),
            ) {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(painterResource(id = R.drawable.ic_arrow_back), contentDescription = null)
                }
            }
        },
        actions = {
            AnimatedVisibility(
                visible = !showNavigationArrow,
                enter = expandHorizontally(),
                exit = shrinkHorizontally()
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onHelpClick) {
                        Icon(painterResource(id = R.drawable.ic_help), contentDescription = null)
                    }
                    IconButton(onClick = onStatisticsClick) {
                        Icon(
                            painterResource(id = R.drawable.ic_bar_chart),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = onArchiveClick) {
                        Icon(painterResource(id = R.drawable.ic_history), contentDescription = null)
                    }
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            painterResource(id = R.drawable.ic_settings),
                            contentDescription = null
                        )
                    }
                }
            }
        },
    )
}