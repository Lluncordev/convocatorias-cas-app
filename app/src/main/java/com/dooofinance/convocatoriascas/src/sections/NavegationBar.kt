package com.dooofinance.convocatoriascas.src.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.navigation.NavigationScreens

@Composable
fun NavegationBar (
    navController: NavController
){
    Row (
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        ButtonIcon(
            iconId = R.drawable.maletin_24,
            description = "Ir al inicio" ,
            text ="Home",
            onClick = {
                navController
                    .navigate(NavigationScreens.HomeScreen.route)
            }
        )

        ButtonIcon(
            iconId = R.drawable.search_24,
            description = "Ir al buscador" ,
            text ="Buscar",
            onClick = {
                navController
                    .navigate(NavigationScreens.SearchOptionScreen.route)
            }
        )
    }
}

@Composable
private  fun ButtonIcon(
    iconId: Int,
    description: String,
    text: String,
    onClick: () -> Unit
){
    Column (
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = description,
            modifier = Modifier
                .size(16.dp),
            tint = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.padding(2.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.secondary
            )
        )
    }
}