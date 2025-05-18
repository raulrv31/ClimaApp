package local.raulrv.climaapp_raulrv.repository

import android.util.Log
import local.raulrv.climaapp_raulrv.model.ClimaResponse
import local.raulrv.climaapp_raulrv.network.RetrofitInstance

class ClimaRepositoryImpl : ClimaRepository {
    override suspend fun obtenerClima(lat: Double, lon: Double): ClimaResponse {
        val response = RetrofitInstance.api.getPronosticoDelTiempo(lat, lon)
        if (response.isSuccessful) {
            Log.d("API_RESPONSE", response.body().toString())
            return response.body() ?: throw Exception("Respuesta vacía")
        } else {
            throw Exception("Error en la API: ${response.code()}")
        }

    }
}