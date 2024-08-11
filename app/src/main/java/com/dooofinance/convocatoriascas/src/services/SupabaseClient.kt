package com.dooofinance.convocatoriascas.src.services

import com.dooofinance.convocatoriascas.model.OfertaModel
import com.dooofinance.convocatoriascas.model.DetalleModel
import com.dooofinance.convocatoriascas.model.EntidadModel
import com.dooofinance.convocatoriascas.src.utils.Constants.CONVOCATORIAS_COLUMNS
import com.dooofinance.convocatoriascas.src.utils.Constants.DETALLE_CONVOCATORIA_COLUMNS
import com.dooofinance.convocatoriascas.src.utils.Constants.END_RANGE
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.postgrest.query.filter.TextSearchType
import io.github.jan.supabase.postgrest.rpc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SupabaseClient {
    lateinit var instance: SupabaseClient

    fun initialize(url: String, key: String) {
        instance = createSupabaseClient(
            supabaseUrl = url,
            supabaseKey = key
        ) {
            install(Postgrest)
        }
    }

    suspend fun getConvocatorias(startRange: Int): List<OfertaModel> = withContext(Dispatchers.IO) {
        val endRange = startRange + (END_RANGE - 1)

        instance.postgrest["Convocatorias"]
            .select(columns = Columns.raw(CONVOCATORIAS_COLUMNS)) {
                order("id", Order.ASCENDING)
                range(startRange.toLong()..endRange.toLong())
                filter {
                    eq("estado", true)
                }
            }
            .decodeList()
    }

    suspend fun getDetalleConvocatoria(id: Int): List<DetalleModel> = withContext(Dispatchers.IO) {
        instance.postgrest["Convocatorias"]
            .select(columns = Columns.raw(DETALLE_CONVOCATORIA_COLUMNS)) {
                filter {
                    eq("id", id)
                }
            }
            .decodeList()
    }

    suspend fun obtenerEntidades() : List<EntidadModel> = withContext(Dispatchers.IO) {
        instance.postgrest.rpc("obtener_entidades").decodeList()
    }

    suspend fun filtarOfertas(query:String, location:String, entidad:String) : List<OfertaModel> = withContext(Dispatchers.IO) {
        instance.postgrest["Convocatorias"]
            .select(columns = Columns.raw(CONVOCATORIAS_COLUMNS)){
                filter {
                    eq("estado", true)

                    if (location.isNotEmpty()){
                        textSearch(
                            column = "departamento",
                            query = location,
                            config = "english",
                            textSearchType = TextSearchType.WEBSEARCH
                        )
                    }

                    if (entidad.isNotEmpty()){
                        textSearch(
                            column = "entidad",
                            query = entidad,
                            config = "english",
                            textSearchType = TextSearchType.WEBSEARCH
                        )
                    }

                    if (query.isNotEmpty()){
                        textSearch(
                            column = "requisitos",
                            query = query,
                            config = "english",
                            textSearchType = TextSearchType.WEBSEARCH
                        )
                    }
                }
            }
            .decodeList()
    }
}
