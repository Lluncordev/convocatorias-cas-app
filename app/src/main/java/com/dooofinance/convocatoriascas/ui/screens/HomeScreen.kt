package com.dooofinance.convocatoriascas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.src.components.Logo
import com.dooofinance.convocatoriascas.src.sections.NavegationBar
import com.dooofinance.convocatoriascas.src.sections.OfertasContent
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    convocatoriaViewModel: ConvocatoriaViewModel
) {
    val convocatorias by convocatoriaViewModel.convocatorias.collectAsState()
    val entidad by convocatoriaViewModel.entidad.collectAsState()

    Scaffold (
        topBar = {
            Logo()
        },
        bottomBar = {
            NavegationBar(navController = navController)
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OfertasContent(
                navController = navController,
                convocatorias = convocatorias,
                entidades = entidad,
                convocatoriaViewModel = convocatoriaViewModel,
                enablePagination = true
            )
        }
    }

}

