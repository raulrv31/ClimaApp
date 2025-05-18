package local.raulrv.climaapp_raulrv.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import local.raulrv.climaapp_raulrv.model.ClimaViewModel
import local.raulrv.climaapp_raulrv.model.ClimaViewModelFactory
import local.raulrv.climaapp_raulrv.repository.ClimaRepositoryImpl
import local.raulrv.climaapp_raulrv.data.UbisPref

/**
 * Pantalla principal que muestra el clima actual de la ubicacion que este seleccionada,
 * si no hay una ubicacion seleccionada se lo hace saber al usuario.
 */
@Composable
fun ActualScreen() {
    val context = LocalContext.current
    val prefs = remember { UbisPref(context) }

    val factory = remember { ClimaViewModelFactory(ClimaRepositoryImpl()) }
    val viewModel: ClimaViewModel = viewModel(factory = factory)

    val clima by viewModel.climaLiveData.observeAsState()
    val error by viewModel.errorLiveData.observeAsState()

    val ubicacionSeleccionada = prefs.obtenerUbicacionSeleccionada()
    if (ubicacionSeleccionada == null) {
        //Si no hay ubicacion seleccionada muestra el mensaje.
        Text("No hay ubicación seleccionada")
    }else {

        LaunchedEffect(Unit) {
            ubicacionSeleccionada?.let {
                viewModel.obtenerClima(it.lat, it.lon)
            }
        }

        Column(
            modifier = Modifier
                .background(Color(0xFFEAF6FF))
                .fillMaxSize()
                .padding(24.dp),

        ) {

            when {
                error != null -> {
                    Text("❌ Error: $error", color = MaterialTheme.colorScheme.error)
                }

                clima != null -> {
                    val actual = clima!!.current_weather
                    Row{
                        //Muestra nombre de la ubicacion seleccionada.
                        Text(
                            text =
                            "${ubicacionSeleccionada.nombre}",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(4.dp)
                        )
                        Icon(Icons.Default.LocationOn, "", Modifier.absolutePadding(right = 4.dp))
                    }

                    //Muestra fecha, hora y si es de dia o nde noche.
                    Row{
                        Text(
                            text = "${actual.time}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray, modifier = Modifier.padding(4.dp)
                        )
                        Text(text = if (actual.is_day == 1) "\uD83C\uDF1E Día" else "\uD83C\uDF1D Noche",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray, modifier = Modifier.padding(4.dp)
                        )
                    }

                    Spacer(Modifier.height(4.dp))

                    //Muestra la temperatura y que clima hace con un emoji.
                    Text(text =
                        "${actual.temperature}°  ${weatherCodeEmoji(actual.weathercode)}",
                        fontSize = 50.sp,style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(4.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    //Muestra el viento en km/h.
                    Text("💨 Viento: ${actual.windspeed} km/h")

                    Spacer(Modifier.height(68.dp))

                    //Despliega una fila del pronostico del clima por horas.
                    PronosticoCardActualScreen()

                }

                else -> {
                    //Muestra un indicador de carga circular mientras no se obtienen los datos.
                    CircularProgressIndicator()
                    Spacer(Modifier.height(8.dp))
                    Text("Cargando datos del clima...")
                }
            }
        }
    }
}
