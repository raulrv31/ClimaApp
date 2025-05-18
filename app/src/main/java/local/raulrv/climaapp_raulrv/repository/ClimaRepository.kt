package local.raulrv.climaapp_raulrv.repository

import local.raulrv.climaapp_raulrv.model.ClimaResponse


interface ClimaRepository {
    /**
     * Obtiene los datos del clima
     * @param lat Latitud de la ubicacion.
     * @param lon Longitud de la ubicacion.
     * @return Devuelve ClimaResponse con los datos metereologicos de la ubicacion indicada.
     */
    suspend fun obtenerClima(lat: Double, lon: Double): ClimaResponse
}