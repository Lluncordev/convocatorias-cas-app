package com.dooofinance.convocatoriascas.src.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BotonRegresar (
    icons: Int,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background (
                color = Color.Transparent,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(icons),
                contentDescription = "Regresar al home",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
                tint = MaterialTheme.colorScheme.secondary )
        }
    }
}