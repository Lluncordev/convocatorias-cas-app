package com.dooofinance.convocatoriascas.src.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.model.OfertaModel

@Composable
fun CardOfertas(
    onClick: ()-> Unit,
    convocatoria: OfertaModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onClick()
            }
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        DetailCardRow(
            iconId = R.drawable.entidad_24,
            contentDescription = "Locacion",
            text = convocatoria.entidad
        )

        Titulos(text = convocatoria.asunto)

        Spacer(modifier = Modifier.height(8.dp))

        DetailCardRow(
            iconId = R.drawable.monedas_24,
            contentDescription = "Monedas",
            text = "S/. ${convocatoria.salario} soles"
        )

        DetailCardRow(
            iconId = R.drawable.ubicacion_24,
            contentDescription = "Ubicación",
            text = convocatoria.departamento
        )

        DetailCardRow(
            iconId = R.drawable.tiempo_24,
            contentDescription = "Tiempo",
            text = "Vigente: ${convocatoria.fecha_fin_postulacion}"
        )
    }
}


