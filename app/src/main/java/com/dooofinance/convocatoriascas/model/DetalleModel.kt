package com.dooofinance.convocatoriascas.model

import kotlinx.serialization.Serializable

@Serializable
data class DetalleModel (
    val id: Int,
    val entidad:String,
    val asunto:String,
    val fecha_fin_postulacion:String,
    val departamento: String,
    val salario: Int,
    val requisitos:String,
    val direccion:String,
    val como_postular:String,
    val link:String,
)
