package local.raulrv.climaapp_raulrv.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*

/**
 * Menu lateral que se despliega desde la izquierda y permite navegar entre diferentes pantallas de la app
 * y cerrarlo si se pulsa en algun sitio que no sea los elementos para navegar entre pantallas.
 */
@Composable
fun MenuLateral(
    navController: NavHostController,
    drawerState: androidx.compose.material3.DrawerState,
    scope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .height(2000.dp)
            .width(300.dp)
            .background(MaterialTheme.colorScheme.surface)

    ) {
        Text(text = "Menú", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        Spacer(Modifier.height(16.dp))

        ItemMenuLateral("Clima actual", Rutas.Actual.route, navController, drawerState, scope)
        ItemMenuLateral("Pronóstico", Rutas.Pronostico.route, navController, drawerState, scope)
        ItemMenuLateral("Cambiar ubicacion", Rutas.Ubicacion.route, navController, drawerState, scope)
    }
}

@Composable
fun ItemMenuLateral(
    nombre: String,
    ruta: String,
    navController: NavController,
    drawerState: androidx.compose.material3.DrawerState,
    scope: CoroutineScope
) {
    TextButton(onClick = {
        navController.navigate(ruta)
        scope.launch { drawerState.close() }
    }) {
        Text(nombre)
    }
}
