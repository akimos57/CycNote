package ru.cyclone.cycnote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.cyclone.cycnote.presentation.screens.edit.EditScreen
import ru.cyclone.cycnote.presentation.screens.main.MainScreen

sealed class Screens(val rout: String) {
    object MainScreen: Screens(rout = "main_screen")
    object DetailsScreen: Screens(rout = "details_screen")
    object AddScreen: Screens(rout = "add_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.rout
    ) {
        composable(route = Screens.MainScreen.rout) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.AddScreen.rout + "/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) {
            EditScreen(navController = navController, it.arguments?.getString("id"))
        }
        composable(route = Screens.AddScreen.rout) {
            EditScreen(navController = navController, it.arguments?.getString("id"))
        }
    }
}