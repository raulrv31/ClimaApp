package local.raulrv.climaapp_raulrv.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Elemento card usado en CambiarUbiScreen para añadir una ubicacion nueva.
 */
@Composable
fun CardAgregarUbi(
    nombre: String,
    lat: String,
    lon: String,
    onNombreChange: (String) -> Unit,
    onLatChange: (String) -> Unit,
    onLonChange: (String) -> Unit,
    onGuardarClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)), // azul claro
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Agregar nueva ubicación", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            //Campo de texto para introducir el nombre de la ubicacion que se desea guardar.
            OutlinedTextField(
                value = nombre,
                onValueChange = onNombreChange,
                label = { Text("Ciudad") },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            //Texto que ejemplifica como se deben de introducir los datos de las coordenadas.
            Text(
                text = "Introduce las coordenadas en grados decimales. Ejemplo: 40.4168, -3.7038",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )

            //Campo de texto para introducir la latitud en grados decimales de la ubicacion que se desea guardar.
            OutlinedTextField(
                value = lat,
                onValueChange = onLatChange,
                label = { Text("Latitud") },
                placeholder = { Text(text = "Latitud") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            //Campo de texto para introducir la longitud en grados decimales de la ubicacion que se desea guardar.
            OutlinedTextField(
                value = lon,
                onValueChange = onLonChange,
                label = { Text("Longitud") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Boton para guardar la ubicacion con los datos introducidos en los campos de texto.
            Button(
                onClick = onGuardarClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Guardar")
            }
        }
    }
}