package local.raulrv.climaapp_raulrv.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Configura el retrofit para hacer las peticiones HTTP a la API
 */
object RetrofitInstance {
    val api: ClimaService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClimaService::class.java)
    }
}



