package local.raulrv.climaapp_raulrv.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import local.raulrv.climaapp_raulrv.data.UbisPref
import local.raulrv.climaapp_raulrv.model.ClimaViewModel
import local.raulrv.climaapp_raulrv.model.ClimaViewModelFactory
import local.raulrv.climaapp_raulrv.repository.ClimaRepositoryImpl

/**
 * Widget de scroll lateral que muestra el pronostico del clima en la pantalla ActualScreen
 */
@Composable
fun PronosticoCardActualScreen() {
    val context = LocalContext.current
    val prefs = remember { UbisPref(context) }
    val ubicacion = prefs.obtenerUbicacionSeleccionada()

    val factory = remember { ClimaViewModelFactory(ClimaRepositoryImpl()) }
    val viewModel: ClimaViewModel = viewModel(factory = factory)

    LaunchedEffect(Unit) {
        ubicacion?.let { viewModel.obtenerClima(it.lat, it.lon) }
    }

    val clima by viewModel.climaLiveData.observeAsState()
    val error by viewModel.errorLiveData.observeAsState()

    when {
        error != null -> {
            Text("❌ Error: $error")
        }

        clima != null -> {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                items(clima!!.hourly.time.size) { i ->
                    val datetime = clima!!.hourly.time[i]
                    val fecha = datetime.substringBefore("T")
                    val hora = datetime.substringAfter("T")
                    val temp = clima!!.hourly.temperature_2m[i]
                    val wCode = clima!!.hourly.weathercode[i]

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .width(120.dp),
                        elevation = 4.dp
                    ) {
                        //Datos a mostrar del pronostico del clima por cada hora.
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(fecha, fontSize = 12.sp, color = Color.Gray)
                            Text(hora, fontSize = 14.sp, color = Color.DarkGray)
                            Text(weatherCodeEmoji(wCode), fontSize = 28.sp)
                            Text("${temp}°", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
            }

        }
    }
}
