package local.raulrv.climaapp_raulrv.model

/**
 * Respuesta de la API,
 */
data class ClimaResponse(
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    val current_weather: ClimaActual,
    val hourly: HourlyData
)

/**
 * Informacion detallada del clima actual.
 */
data class ClimaActual(
    val time: String,
    val interval: Int,
    val temperature: Double,
    val windspeed: Double,
    val winddirection: Int,
    val is_day: Int,
    val weathercode: Int
)

/**
 * Informacion por horas en foirmato de listas.
 */
data class HourlyData(
    val time: List<String>,
    val temperature_2m: List<Double>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>
)
