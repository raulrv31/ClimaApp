package local.raulrv.climaapp_raulrv.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import local.raulrv.climaapp_raulrv.repository.ClimaRepository
/**
 * Factory para crear instancias de ClimaViewModel con un repositorio inyectado.
 */
class ClimaViewModelFactory(private val repository: ClimaRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
            return ClimaViewModel(repository) as T
        }
        throw IllegalArgumentException("IllegalArgumentException")
    }
}