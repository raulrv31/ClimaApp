package local.raulrv.climaapp_raulrv.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import local.raulrv.climaapp_raulrv.data.UbisPref
import local.raulrv.climaapp_raulrv.model.ClimaViewModel
import local.raulrv.climaapp_raulrv.model.ClimaViewModelFactory
import local.raulrv.climaapp_raulrv.network.RetrofitInstance
import local.raulrv.climaapp_raulrv.repository.ClimaRepositoryImpl
import local.raulrv.climaapp_raulrv.model.ClimaResponse
import local.raulrv.climaapp_raulrv.model.HourlyData

/**
 * Pantalla que muestra de forma detallada el pronostico del clima por horas.
 */
@Composable
fun PronosticoScreen() {

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

            Column(Modifier.background(Color(0xFFEAF6FF))) {
                Row (
                    Modifier
                        .padding(16.dp)
                        .background(Color(0xFFD0E9FF))
                        .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                )
                {

                    //Datos a mostrar del pronostico del clima

                    Text("Fecha",
                        Modifier
                            .weight(1f)
                            .padding(16.dp), color = Color(0xFF0D47A1), fontWeight = FontWeight.Bold)
                    Text("Temperatura",
                        Modifier
                            .weight(1f)
                            .padding(16.dp), color = Color(0xFF0D47A1), fontWeight = FontWeight.Bold)
                    Text("Viento",
                        Modifier
                            .weight(1f)
                            .padding(16.dp), color = Color(0xFF0D47A1), fontWeight = FontWeight.Bold)
                }

                LazyColumn(Modifier.background(Color(0xFFEAF6FF))) {
                    items(clima!!.hourly.time.size) { i ->
                        val temp = clima!!.hourly.temperature_2m[i]
                        val viento = clima!!.hourly.windspeed_10m[i]
                        val wCode = clima!!.hourly.weathercode[i]

                        //Separa la fecha y hora del formato unificado que da la API
                        val datetime = clima!!.hourly.time[i]
                        val fecha = datetime.substringBefore("T")
                        val hora = datetime.substringAfter("T")

                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .clip(RoundedCornerShape(8.dp))
                                .height(IntrinsicSize.Min)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color(0xFFD0E9FF))
                                    .padding(16.dp)
                                    .fillMaxHeight()
                            ) {
                                Row {
                                    Column{
                                        Text(
                                            "$fecha",
                                            color = Color(0xFF0D47A1),
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            "$hora ${weatherCodeEmoji(wCode)}",
                                            color = Color(0xFF0D47A1),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color(0xFFD0E8FF))
                                    .padding(16.dp)
                                    .fillMaxHeight()
                            ) {
                                Text("${temp}ºC", color = Color(0xFF0D47A1))
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color(0xFFD0E8FF))
                                    .padding(16.dp)
                                    .fillMaxHeight()
                            ) {
                                Text("${viento} km/h", color = Color(0xFF0D47A1))
                            }
                        }
                    }
                }
            }



        }



    }
}