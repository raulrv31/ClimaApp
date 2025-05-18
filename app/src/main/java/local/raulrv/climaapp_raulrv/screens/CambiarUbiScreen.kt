package local.raulrv.climaapp_raulrv.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import local.raulrv.climaapp_raulrv.data.UbisPref
import local.raulrv.climaapp_raulrv.model.Ubicacion


@Composable
fun CambiarUbiScreen(navController: NavHostController) {
    val context = LocalContext.current
    val prefs = remember { UbisPref(context) }

    var nombre by remember { mutableStateOf("") }
    var lat by remember { mutableStateOf("") }
    var lon by remember { mutableStateOf("") }

    val ubicaciones = remember { mutableStateListOf<Ubicacion>() }

    LaunchedEffect(Unit) {
        ubicaciones.clear()
        ubicaciones.addAll(prefs.obtenerUbicaciones())
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        item {
            //Panel para añadir los datos (nombre, latitud y longitud) de la ubicacion que se desea guardar.
            CardAgregarUbi(
                nombre = nombre,
                lat = lat,
                lon = lon,
                onNombreChange = { nombre = it },
                onLatChange = { lat = it },
                onLonChange = { lon = it },
                onGuardarClick = {
                    val latNum = lat.toDoubleOrNull()
                    val lonNum = lon.toDoubleOrNull()

                    if (nombre.isBlank() || latNum == null || lonNum == null) {
                        Toast.makeText(context, "Por favor, completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
                    } else {
                        val nueva = Ubicacion(nombre.trim(), latNum, lonNum)
                        prefs.guardarUbicacion(nueva)
                        ubicaciones.add(nueva)
                        nombre = ""
                        lat = ""
                        lon = ""
                    }
                }
            )

            Spacer(Modifier.height(24.dp))
            Text("Ubicaciones guardadas:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
        }

        //Lista de ubicaciones que se han guardado.
        items(ubicaciones) { ubi ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            prefs.guardarUbicacionSeleccionada(ubi)
                            Toast
                                .makeText(
                                    context,
                                    "Ubicación seleccionada: ${ubi.nombre}",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                            navController.navigate(Rutas.Actual.route)
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Row{
                                Icon(Icons.Default.LocationOn, "", Modifier.absolutePadding(right = 4.dp))
                                Text(ubi.nombre, style = MaterialTheme.typography.titleMedium)
                            }
                            Text("Lat: ${ubi.lat}, Lon: ${ubi.lon}", style = MaterialTheme.typography.bodyMedium)
                        }
                        //Boton que permte eliminar una ubicacion guardada.
                        IconButton(onClick = {
                            prefs.eliminarUbicacion(ubi)
                            ubicaciones.remove(ubi)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = Color.Red
                            )
                        }

                }
            }
        }


    }
}


