package local.raulrv.climaapp_raulrv.model

/**
 * Modelo para representar una ubicacion.
 * @property nombre Nombre de referencia para la ubicacion.
 * @property lat Latitud en grados decimales.
 * @property lon Longitud en grados decimales.
 */
data class Ubicacion(val nombre: String, val lat: Double, val lon: Double)
