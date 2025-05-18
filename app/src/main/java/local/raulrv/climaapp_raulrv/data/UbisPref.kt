package local.raulrv.climaapp_raulrv.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import local.raulrv.climaapp_raulrv.model.Ubicacion

/**
 * Gestion de las ubicaciones guardadas y la seleccionada.
 * Permite guardar, obtener y eliminar ubicaciones segun las acciones del usuario.
 */
class UbisPref(context: Context) {

    private val prefs = context.getSharedPreferences("clima_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    private val keyLista = "lista_ubicaciones"
    private val keySeleccionada = "ubicacion_seleccionada"

    fun guardarUbicacion(nueva: Ubicacion) {
        val lista = obtenerUbicaciones().toMutableList()
        lista.add(nueva)
        val json = gson.toJson(lista)
        prefs.edit().putString(keyLista, json).apply()
    }

    fun obtenerUbicaciones(): List<Ubicacion> {
        val json = prefs.getString(keyLista, null) ?: return emptyList()
        val type = object : TypeToken<List<Ubicacion>>() {}.type
        return gson.fromJson(json, type)
    }

    fun eliminarUbicacion(ubicacion: Ubicacion) {
        val lista = obtenerUbicaciones().toMutableList()
        lista.removeAll { it.nombre == ubicacion.nombre && it.lat == ubicacion.lat && it.lon == ubicacion.lon }
        val json = gson.toJson(lista)
        prefs.edit().putString(keyLista, json).apply()
    }

    fun guardarUbicacionSeleccionada(ubicacion: Ubicacion) {
        val json = gson.toJson(ubicacion)
        prefs.edit().putString(keySeleccionada, json).apply()
    }

    fun obtenerUbicacionSeleccionada(): Ubicacion? {
        val json = prefs.getString(keySeleccionada, null) ?: return null
        return gson.fromJson(json, Ubicacion::class.java)
    }
}
