package com.dooofinance.convocatoriascas.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dooofinance.convocatoriascas.model.OfertaModel
import com.dooofinance.convocatoriascas.model.DetalleModel
import com.dooofinance.convocatoriascas.model.EntidadModel
import com.dooofinance.convocatoriascas.src.services.SupabaseClient
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ConvocatoriaViewModel : ViewModel() {
    private val remoteConfig: FirebaseRemoteConfig by lazy { Firebase.remoteConfig }

    private val _convocatorias = MutableStateFlow<List<OfertaModel>>(emptyList())
    val convocatorias: StateFlow<List<OfertaModel>> = _convocatorias.asStateFlow()

    private val _resultadoBusqueda = MutableStateFlow<List<OfertaModel>>(emptyList())
    val resultadoBusqueda: StateFlow<List<OfertaModel>> = _resultadoBusqueda.asStateFlow()

    private val _entidad = MutableStateFlow<List<EntidadModel>>(emptyList())
    val entidad: StateFlow<List<EntidadModel>> = _entidad.asStateFlow()

    private val _detalleConvocatoria = MutableStateFlow<List<DetalleModel>>(emptyList())
    val detalleConvocatoria: StateFlow<List<DetalleModel>> = _detalleConvocatoria.asStateFlow()

    init {
        initializeRemoteConfig()
    }

    private fun initializeRemoteConfig() {
        viewModelScope.launch {
            try {
                remoteConfig.setConfigSettingsAsync(remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 3600
                })
                remoteConfig.fetchAndActivate().await()
                initSupabase()
            } catch (e: Exception) {
                Log.e("ConvocatoriaViewModel", "Error initializing Remote Config", e)
            }
        }
    }

    private fun initSupabase() {
        val supabaseUrl = remoteConfig.getString("SUPABASE_URL")
        val supabaseAnonKey = remoteConfig.getString("SUPABASE_ANON_KEY")
        SupabaseClient.initialize(supabaseUrl, supabaseAnonKey)
        ofertasDisponibles(0)
    }

    fun ofertasDisponibles(startRange: Int) {
        viewModelScope.launch {
            try {
                val nuevasOfertas = SupabaseClient.getConvocatorias(startRange)
                val entidadesDisponibles = SupabaseClient.obtenerEntidades()
                _convocatorias.update { it + nuevasOfertas }
                _entidad.update {entidadesDisponibles}
            } catch (e: Exception) {
                Log.e("ConvocatoriaViewModel", "Error fetching convocatorias", e)
            }
        }
    }

    fun detalleOferta(id: Int) {
        viewModelScope.launch {
            try {
                _detalleConvocatoria.value = SupabaseClient.getDetalleConvocatoria(id)
            } catch (e: Exception) {
                Log.e("ConvocatoriaViewModel", "Error fetching detalle convocatoria", e)
            }
        }
    }

    fun buscarOfertas (query:String, locacion:String, entidad:String) {
        viewModelScope.launch {
            try {
                _resultadoBusqueda.value = SupabaseClient.filtarOfertas(
                    query = query,
                    location = locacion,
                    entidad = entidad)
            } catch (e: Exception) {
                Log.e("ConvocatoriaViewModel", "Error al filtrar convocatorias", e)
            }
        }
    }

    fun clearDetalleConvocatoria() {
        _detalleConvocatoria.value = emptyList()
    }

}