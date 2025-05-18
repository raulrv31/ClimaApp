package local.raulrv.climaapp_raulrv.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * Rutass de la navegacion de la aplicacion entre pantallas.
 */
sealed class Rutas(val route: String) {
    object Actual : Rutas("actual")
    object Pronostico : Rutas("pronostico")
    object Ubicacion : Rutas("ubicacion")
}

@Composable
fun RutasNav(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Rutas.Actual.route, modifier = modifier) {
        composable(Rutas.Actual.route) { ActualScreen() }
        composable(Rutas.Pronostico.route) { PronosticoScreen() }
        composable(Rutas.Ubicacion.route) { CambiarUbiScreen(navController) }
    }
}
