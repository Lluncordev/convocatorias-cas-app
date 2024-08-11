package com.dooofinance.convocatoriascas.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.src.components.BotonRegresar
import com.dooofinance.convocatoriascas.src.sections.DescargarConvocatorias
import com.dooofinance.convocatoriascas.src.sections.DetalleOfertasContent
import com.dooofinance.convocatoriascas.ui.viewModel.AdmobViewModel
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun DetailJobScreen(
    id: Int?,
    convocatoriaViewModel: ConvocatoriaViewModel,
    adViewModel: AdmobViewModel,
    navController: NavController
) {
    val detailConvocatoria by convocatoriaViewModel.detalleConvocatoria.collectAsState()

    LaunchedEffect(id) {
        id?.let {
            convocatoriaViewModel.detalleOferta(it)
            adViewModel.loadAd()
        }
    }

    BackHandler {
        adViewModel.loadAd()
        navController.popBackStack()
        convocatoriaViewModel.clearDetalleConvocatoria()
    }

    Scaffold(
        topBar = {
            BotonRegresar(
                icons = R.drawable.regresar_24,
                onClick = {
                    convocatoriaViewModel.clearDetalleConvocatoria()
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            if (detailConvocatoria.isNotEmpty()) {
                DescargarConvocatorias(detailConvocatoria[0].link, adViewModel)
            }
        }
    ){ paddingValues ->
        if (detailConvocatoria.isNotEmpty()) {
            DetalleOfertasContent(
                convocatoriaViewModel = convocatoriaViewModel,
                navController = navController,
                detail = detailConvocatoria[0],
                modifier = Modifier.padding(paddingValues))
        } else {
            LoadingDetail()
        }
    }
}

@Composable
private fun LoadingDetail(){
    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cargando informacion...",
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.tertiary
            )
        )
    }
}
