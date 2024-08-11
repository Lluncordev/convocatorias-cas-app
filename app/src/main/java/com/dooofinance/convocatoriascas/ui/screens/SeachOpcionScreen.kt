package com.dooofinance.convocatoriascas.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.navigation.NavigationScreens
import com.dooofinance.convocatoriascas.src.components.BotonRegresar
import com.dooofinance.convocatoriascas.src.sections.NavegationBar
import com.dooofinance.convocatoriascas.src.sections.SearchOptionContent
import com.dooofinance.convocatoriascas.ui.viewModel.AdmobViewModel
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun SeachOpcionScreen (
    navController: NavController,
    convocatoriaViewModel: ConvocatoriaViewModel,
    admobViewModel: AdmobViewModel
) {
    Scaffold (
        topBar = {
            BotonRegresar(
                icons = R.drawable.regresar_24,
                onClick = {
                    navController.navigate(NavigationScreens.HomeScreen.route)
                }
            )
        },
        bottomBar = {
            NavegationBar(navController = navController)
        }
    ){ paddingValues ->
        SearchOptionContent(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            convocatoriaViewModel = convocatoriaViewModel,
            admobViewModel = admobViewModel
        )
    }
}