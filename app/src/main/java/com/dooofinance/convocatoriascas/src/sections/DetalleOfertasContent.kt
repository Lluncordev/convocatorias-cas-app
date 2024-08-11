package com.dooofinance.convocatoriascas.src.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.model.DetalleModel
import com.dooofinance.convocatoriascas.navigation.NavigationScreens
import com.dooofinance.convocatoriascas.src.components.CardOfertas
import com.dooofinance.convocatoriascas.src.components.DetailCardRow
import com.dooofinance.convocatoriascas.src.components.Responsabilidad
import com.dooofinance.convocatoriascas.src.components.Titulos
import com.dooofinance.convocatoriascas.src.components.anuncios.BannerAdmob
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun DetalleOfertasContent(
    convocatoriaViewModel: ConvocatoriaViewModel,
    navController: NavController,
    detail: DetalleModel,
    modifier: Modifier
) {
    val ofertasRelacionadas by convocatoriaViewModel.resultadoBusqueda.collectAsState()

    LaunchedEffect (detail.entidad) {
        convocatoriaViewModel.buscarOfertas(
            query = "",
            locacion = "",
            entidad = detail.entidad)
    }

    Column(
        modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .padding(10.dp, 0.dp)
        ) {
            Titulos(text = detail.asunto)

            Spacer(modifier = Modifier.height(10.dp))

            DetailCardRow(
                iconId = R.drawable.entidad_24,
                contentDescription = "Entidad",
                text = "Entidad: ${detail.entidad}")

            DetailCardRow(
                iconId = R.drawable.monedas_24,
                contentDescription = "Salario",
                text = "Salario: S/. ${detail.salario} soles")

            DetailCardRow(
                iconId = R.drawable.ubicacion_24,
                contentDescription = "Ubicación",
                text = "Ubicación: ${detail.departamento}")
        }

        LazyColumn {

            item {
                Column (modifier = Modifier.padding(10.dp)) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Titulos(text = "Detalle de la oferta")

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = detail.requisitos,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = detail.direccion,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Titulos(text = "¿Cómo postular?")

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = detail.como_postular,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Responsabilidad()

                    Spacer(modifier = Modifier.height(10.dp))

                    Titulos(text = "Ofertas relacionadas de ${detail.entidad}")
                }
            }

            itemsIndexed(ofertasRelacionadas) { index, ofertas ->
                ofertas.let {
                    CardOfertas(
                        onClick = {
                            navController
                                .navigate(
                                    NavigationScreens
                                        .DetailJobScreen
                                        .createRoute(ofertas.id)
                                )
                        }
                        , convocatoria = ofertas)

                    if (index % 5 == 0) {
                        BannerAdmob()
                    }
                }
            }
        }
    }
}
