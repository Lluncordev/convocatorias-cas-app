package com.dooofinance.convocatoriascas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dooofinance.convocatoriascas.R
import com.dooofinance.convocatoriascas.navigation.NavigationScreens
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun SplashScreen(navController: NavController, viewModel: ConvocatoriaViewModel) {
    val convocatorias by viewModel.convocatorias.collectAsState()

    Slpash()

    when{
        convocatorias.isNotEmpty() -> {
            navController.popBackStack()
            navController.navigate(NavigationScreens.HomeScreen.route)
        }
    }
}

@Composable
fun Slpash(){
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo app",
            Modifier.size(150.dp, 150.dp))

        Text(
            text = "Accede a las vacantes del sector público, ahorra tiempo en tu búsqueda laboral.",
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.secondary
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplasView(){
    Slpash()
}