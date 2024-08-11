package com.dooofinance.convocatoriascas.src.sections

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.navigation.NavigationScreens
import com.dooofinance.convocatoriascas.src.components.CardSearch
import com.dooofinance.convocatoriascas.ui.viewModel.AdmobViewModel
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun SearchOptionContent (
    modifier: Modifier,
    navController: NavController,
    convocatoriaViewModel: ConvocatoriaViewModel,
    admobViewModel: AdmobViewModel
) {
    val adReady by admobViewModel.adReady.collectAsState()
    val adViewComplete by admobViewModel.adViewComplete.collectAsState()

    var _carrera by remember { mutableStateOf("") }
    var _locacion by remember { mutableStateOf("") }

    if (adViewComplete) {
        navController.navigate(NavigationScreens.SearchResultScreen.route)
        admobViewModel.loadAd()
    }

    Column (
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Tu búsqueda de empleo empieza aquí.",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp, 5.dp))

        CardSearch(
            active = true,
            query = _carrera,
            onQueryChange = {_carrera = it},
            iconId = R.drawable.school_24px,
            placeholder = "Carrera: Derecho, Educacion, Ing Civil ...",
            onClick = {}
        )

        CardSearch(
            active = true,
            query = _locacion,
            onQueryChange = {_locacion = it},
            iconId = R.drawable.ubicacion_24,
            placeholder = "Departamento: Lima, Ica ...",
            onClick = {}
        )

        ButtonSearch(
            onclick = {
                convocatoriaViewModel.buscarOfertas(
                    query = _carrera,
                    locacion = _locacion,
                    entidad = ""
                )

                if (adReady) {
                    admobViewModel.showAd()
                } else {
                    navController
                        .navigate(
                            NavigationScreens
                            .SearchResultScreen
                            .route
                        )
                }
            }
        )
    }
}

@Composable
private fun ButtonSearch(
    onclick: ()-> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp)
            .border(
                width = 1.5.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable {
                onclick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.anuncio_24),
            contentDescription = "bases del concurso",
            modifier = Modifier
                .size(18.dp),
            tint = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = "BUSCAR EMPLEO",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
    }
}