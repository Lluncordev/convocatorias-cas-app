package com.dooofinance.convocatoriascas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.src.components.BotonRegresar
import com.dooofinance.convocatoriascas.src.sections.NavegationBar
import com.dooofinance.convocatoriascas.src.sections.OfertasContent
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun SeachResultScreen (
    navController : NavController,
    convocatoriaViewModel :ConvocatoriaViewModel
) {
    val resultadoBusqueda by convocatoriaViewModel.resultadoBusqueda.collectAsState()

    Scaffold (
        topBar = {
            BotonRegresar(
                icons = R.drawable.regresar_24,
                onClick = {
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            NavegationBar( navController = navController)
        }
    ){ paddingValues ->
        Column(Modifier.padding(paddingValues)) {

            Text(text = "Estas son las ofertas disponible",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.tertiary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp)
            )

            OfertasContent(
                navController = navController,
                convocatorias = resultadoBusqueda,
                entidades = emptyList(),
                convocatoriaViewModel = convocatoriaViewModel,
                enablePagination = false
            )
        }
    }
}