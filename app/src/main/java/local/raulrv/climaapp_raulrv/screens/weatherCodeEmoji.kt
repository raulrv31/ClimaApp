package local.raulrv.climaapp_raulrv.screens

/**
 * Funcion para representar en forma de emoji el codigo de tiempo que se obtiene de la API Open-Meteo.
 */
fun weatherCodeEmoji(code: Int): String = when (code) {
    0 -> "☀️"
    1 -> "🌤"
    2 -> "⛅"
    3 -> "☁️"
    45, 48 -> "🌫"
    51, 53, 55 -> "🌦"
    56, 57 -> "🌧❄️"
    61, 63, 65 -> "🌧"
    66, 67 -> "🌧❄️"
    71, 73, 75 -> "❄️"
    77 -> "🧊"
    80, 81, 82 -> "🌧💨"
    85, 86 -> "❄️💨"
    95, 96, 99 -> "⛈"
    else -> "❓"
}
