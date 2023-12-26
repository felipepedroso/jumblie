package br.pedroso.jumblie.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import br.pedroso.jumblie.Screens
import br.pedroso.jumblie.game.GameScreen
import br.pedroso.jumblie.helpdialog.HelpDialog
import br.pedroso.jumblie.settings.SettingsScreen
import br.pedroso.jumblie.startscreen.StartScreen
import br.pedroso.jumblie.statistics.ArchiveScreen
import br.pedroso.jumblie.statistics.StatisticsScreen
import br.pedroso.jumblie.ui.components.Credits
import br.pedroso.jumblie.ui.components.GameTopAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = Screens.valueOf(
        backStackEntry?.destination?.route ?: Screens.Start.name
    )
    Scaffold(
        modifier = modifier,
        topBar = {
            GameTopAppBar(
                onHelpClick = { navController.navigate(Screens.Help.name) },
                onSettingsClick = { navController.navigate(Screens.Settings.name) },
                onStatisticsClick = { navController.navigate(Screens.Statistics.name) },
                onArchiveClick = { navController.navigate(Screens.Archive.name) },
                showNavigationArrow = currentScreen.showNavigationArrow,
                onNavigationIconClick = navController::navigateUp
            )
        },
        bottomBar = { Credits(modifier = Modifier.fillMaxWidth()) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Start.name,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            composable(route = Screens.Start.name) {
                StartScreen(
                    modifier = Modifier.fillMaxSize(),
                    viewModel = viewModel(),
                    onStartClick = { navController.navigate(Screens.Game.name) })
            }

            composable(route = Screens.Game.name) {
                GameScreen(
                    modifier = Modifier.fillMaxSize(),
                    viewModel = viewModel()
                )
            }

            composable(route = Screens.Settings.name) {
                SettingsScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = Screens.Archive.name) {
                ArchiveScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = Screens.Statistics.name) {
                StatisticsScreen(modifier = Modifier.fillMaxSize())
            }

            dialog(route = Screens.Help.name) {
                HelpDialog { navController.popBackStack() }
            }
        }
    }
}