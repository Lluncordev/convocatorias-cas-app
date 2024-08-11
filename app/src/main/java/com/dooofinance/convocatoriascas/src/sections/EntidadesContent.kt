package com.dooofinance.convocatoriascas.src.sections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.model.EntidadModel
import com.dooofinance.convocatoriascas.navigation.NavigationScreens
import com.dooofinance.convocatoriascas.src.components.CardEntidades
import com.dooofinance.convocatoriascas.src.components.CardSearch
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun EntidadesContent (
    navController: NavController,
    entidades: List<EntidadModel?>,
    convocatoriaViewModel: ConvocatoriaViewModel,
){
    var query by remember { mutableStateOf("")}

    CardSearch(
        active = false ,
        query = query,
        onQueryChange = {query = it},
        iconId = R.drawable.search_24,
        placeholder = "Buscar convocatoria por carrera o locacion ...",
        onClick = {
            navController.navigate(NavigationScreens.SearchOptionScreen.route)
        }
    )

    LazyRow (modifier = Modifier
        .fillMaxWidth()
    ){
        itemsIndexed(entidades){ index, entidad ->
            entidad?.let {
                CardEntidades(
                    entidadModel = entidad,
                    onclick = {
                        convocatoriaViewModel.buscarOfertas(
                            query = "",
                            locacion = "",
                            entidad = entidad.entidad
                        )
                        navController.navigate(NavigationScreens.SearchResultScreen.route)
                })
            }
        }
    }
}