package com.dooofinance.convocatoriascas.src.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.model.EntidadModel

@Composable

fun CardEntidades (
    entidadModel: EntidadModel,
    onclick: () -> Unit
) {
    Column(
        modifier = Modifier
            .widthIn(min = 100.dp, max = 200.dp)
            .height(100.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onclick()
            }
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        DetailCardRow(
            iconId = R.drawable.entidad_24,
            contentDescription = "Locacion",
            text = entidadModel.entidad
        )
    }
}