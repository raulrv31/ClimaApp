package local.raulrv.climaapp_raulrv

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import local.raulrv.climaapp_raulrv.screens.MenuLateral
import local.raulrv.climaapp_raulrv.screens.MiToolbar
import local.raulrv.climaapp_raulrv.screens.Rutas
import local.raulrv.climaapp_raulrv.screens.RutasNav
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.*

/**
 * Principal gestion de la interfaz del usuario, manejando la navegacion y la barra superior.
 */
@Composable
fun ClimaApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val rutaActual = navController.currentBackStackEntryAsState().value?.destination?.route

    //Titulo de la barra superior en funcion de la pantalla en la que se encuentre el usuario.
    val titulo = when (rutaActual) {
        Rutas.Actual.route -> "Clima actual"
        Rutas.Pronostico.route -> "Pronóstico"
        Rutas.Ubicacion.route -> "Cambiar ubicación"
        else -> "App clima"
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuLateral(navController, drawerState, scope)
        }
    ) {
        Scaffold(
            topBar = {
                MiToolbar(
                    titulo = titulo,
                    navController = navController,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        ) { innerPadding ->
            RutasNav(navController, Modifier.padding(innerPadding))
        }
    }
}

