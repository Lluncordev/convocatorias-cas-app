package com.dooofinance.convocatoriascas.src.utils

object Constants {
    const val END_RANGE = 11
    const val ENABLE_TEST_APP = true
    const val DESCARGO_RESPONSABILIDAD = "CONVOCATORIAS CAS: Es una aplicación informativa e independiente que no representa a ninguna entidad gubernamental. Es un servicio voluntario y gratuito que se mantiene con recursos propios."

    //Consultas Supabase
    const val CONVOCATORIAS_COLUMNS = """
    id,
    entidad, 
    asunto,
    departamento,
    salario,
    fecha_fin_postulacion
"""
    const val DETALLE_CONVOCATORIA_COLUMNS = """
    id,
    entidad,
    asunto,
    fecha_fin_postulacion,
    departamento,
    salario,
    requisitos,
    direccion,
    como_postular,
    link
"""

    //ID ADMOB PROD
    const val ID_BANNER_PROD = "ca-app-pub-7972088385766925/4561556643"
    const val ID_INTERSTISTIAL_PROD = "ca-app-pub-7972088385766925/5003074617"

    //ID AMOB QAS
    const val ID_BANNER_TEST = "ca-app-pub-3940256099942544/9214589741"
    const val ID_INTERSTISTIAL_TEST = "ca-app-pub-3940256099942544/5224354917"

}





