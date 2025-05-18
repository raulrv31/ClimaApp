package local.raulrv.climaapp_raulrv.screens

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import local.raulrv.climaapp_raulrv.screens.Rutas

/**
 * Barra superior (TopAppBar) que muestra el nombre de la pantalla.
 * Tiene un boton que permite navegar a la pantalla anterior, otro para ir a la pantalla CambiarUbiScreen
 * y otro boton que permite abrir un menu lateral desde la izquierda.
 */
@Composable
fun MiToolbar(titulo: String, navController: NavHostController, onMenuClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = titulo)},
        backgroundColor = Color(0xFF1976D2),
        contentColor = Color.White,
        navigationIcon = {
            //Boton con icono que permite navegar a la anterior pantalla.
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
            }
        },
        actions = {
            //Boton con icono que permite navegar a la pantalla CambiarUbiScreen
            IconButton(onClick = { navController.navigate(Rutas.Ubicacion.route) }) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Cambiar ubicación")
            }
            //Boton con icono que permite desplegar el menu lateral MenuLateral
            IconButton(onClick = { onMenuClick() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menú")
            }
        }
    )
}
