package com.dooofinance.convocatoriascas.model

import kotlinx.serialization.Serializable

@Serializable
data class OfertaModel(
    val id: Int,
    val asunto:String,
    val entidad:String,
    val departamento:String,
    val salario:Int,
    val fecha_fin_postulacion:String,
)
