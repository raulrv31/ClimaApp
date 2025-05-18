package local.raulrv.climaapp_raulrv.network

import local.raulrv.climaapp_raulrv.model.ClimaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Define peticiones de la API Open-Meteo
 */


interface ClimaService {

    /**
     * Solicita los datos de la prediccion del tiempo para una ubicacion
     */
    @GET("v1/forecast")
    suspend fun getPronosticoDelTiempo(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m,weathercode,windspeed_10m",
        @Query("models") models: String = "bom_access_global",
        @Query("current_weather") currentWeather: Boolean = true,
        @Query("timezone") timezone: String = "auto"
    ): Response<ClimaResponse>
}


