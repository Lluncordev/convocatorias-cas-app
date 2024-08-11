package com.dooofinance.convocatoriascas.src.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.model.EntidadModel
import com.dooofinance.convocatoriascas.model.OfertaModel
import com.dooofinance.convocatoriascas.navigation.NavigationScreens
import com.dooofinance.convocatoriascas.src.components.CardOfertas
import com.dooofinance.convocatoriascas.src.components.anuncios.BannerAdmob
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel
import kotlinx.coroutines.delay

@Composable
fun OfertasContent(
    navController: NavController,
    convocatorias: List<OfertaModel>,
    entidades: List<EntidadModel>,
    convocatoriaViewModel: ConvocatoriaViewModel,
    enablePagination: Boolean,
) {

    LazyColumn {
        if (entidades.isNotEmpty()) {
            item {
                EntidadesContent(
                    navController = navController,
                    entidades = entidades,
                    convocatoriaViewModel = convocatoriaViewModel
                )
            }
        }

        itemsIndexed(convocatorias) { index, convocatoria ->
            val isVisible = remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                delay(index * 100L)
                isVisible.value = true
            }

            AnimatedVisibility(
                visible = isVisible.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CardOfertas(
                    onClick = {
                        navController.navigate(
                            NavigationScreens
                                .DetailJobScreen
                                .createRoute(convocatoria.id)
                        )
                    }, convocatoria = convocatoria
                )
            }

            if (index % 5 == 0) {
                BannerAdmob()
            }

            if (enablePagination && index == convocatorias.lastIndex) {
                LaunchedEffect(Unit) {
                    convocatoriaViewModel.ofertasDisponibles(index)
                }
            }
        }
    }
}