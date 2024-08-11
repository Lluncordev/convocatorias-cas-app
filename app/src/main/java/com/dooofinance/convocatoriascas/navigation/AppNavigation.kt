package com.dooofinance.convocatoriascas.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dooofinance.convocatoriascas.ui.screens.DetailJobScreen
import com.dooofinance.convocatoriascas.ui.screens.HomeScreen
import com.dooofinance.convocatoriascas.ui.screens.SeachOpcionScreen
import com.dooofinance.convocatoriascas.ui.screens.SeachResultScreen
import com.dooofinance.convocatoriascas.ui.screens.SplashScreen
import com.dooofinance.convocatoriascas.ui.viewModel.AdmobViewModel
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel

@Composable
fun AppNavigation (
    convocatoriaViewModel: ConvocatoriaViewModel,
    admobViewModel: AdmobViewModel,
    modifier: Modifier
){
    val navController = rememberNavController()

    Box(
        modifier.fillMaxSize()
    ) {
        NavHost(navController = navController, startDestination = NavigationScreens.SplashScreen.route){

            composable(route=NavigationScreens.SplashScreen.route){
                SplashScreen(navController, convocatoriaViewModel)
            }

            composable(route=NavigationScreens.HomeScreen.route){
                HomeScreen(navController, convocatoriaViewModel)
            }

            composable(
                route=NavigationScreens.DetailJobScreen.route,
                arguments = listOf(navArgument("id") { type=NavType.IntType })
            ){ backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                DetailJobScreen(id = id,
                    convocatoriaViewModel = convocatoriaViewModel,
                    adViewModel = admobViewModel,
                    navController = navController
                )
            }
            
            composable(route = NavigationScreens.SearchOptionScreen.route){
                SeachOpcionScreen(
                    navController = navController,
                    convocatoriaViewModel = convocatoriaViewModel,
                    admobViewModel = admobViewModel
                )
            }
            
            composable(route = NavigationScreens.SearchResultScreen.route){
                SeachResultScreen(
                    navController = navController,
                    convocatoriaViewModel = convocatoriaViewModel
                )
            }
        }
    }
}