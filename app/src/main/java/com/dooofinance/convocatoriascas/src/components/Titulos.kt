package com.dooofinance.convocatoriascas.src.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dooofinance.convocatoriascas.src.function.formatAsSentence

@Composable
fun Titulos(text: String) {
    Text(
        text = formatAsSentence(text),
        style = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.tertiary
        )
    )
}