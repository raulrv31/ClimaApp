package local.raulrv.climaapp_raulrv.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import local.raulrv.climaapp_raulrv.repository.ClimaRepository

/**
 * Se encarga de obtener y mostrar los datos del clima obtenidos del repositorio.
 */
class ClimaViewModel(private val repository: ClimaRepository) : ViewModel() {

    private val _climaLiveData = MutableLiveData<ClimaResponse>()
    val climaLiveData: LiveData<ClimaResponse> = _climaLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    /**
     * Peticion para obtener el clima de una ubicacion (lat, lon) concreta.
     */
    fun obtenerClima(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val clima = repository.obtenerClima(lat, lon)
                _climaLiveData.value = clima
            } catch (e: Exception) {
                _errorLiveData.value = "Error: ${e.message}"
            }
        }
    }

}

