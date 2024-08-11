package com.dooofinance.convocatoriascas.src.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dooofinance.convocatoriascas.src.utils.Constants.DESCARGO_RESPONSABILIDAD

@Composable
fun Responsabilidad() {
    Text(
        text = DESCARGO_RESPONSABILIDAD,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.secondary
        ),
        fontSize = 14.sp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.small
            )
            .padding(10.dp)
    )
}