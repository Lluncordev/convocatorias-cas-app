package com.dooofinance.convocatoriascas.navigation

sealed class NavigationScreens (val route:String){
    object SplashScreen: NavigationScreens("splash_screen")
    object HomeScreen: NavigationScreens("home_screen")
    object DetailJobScreen: NavigationScreens("detail_job_screen/{id}"){ fun createRoute(id:Int) = "detail_job_screen/$id" }
    object SearchOptionScreen: NavigationScreens("seach_screen")
    object SearchResultScreen: NavigationScreens("seach_result_screen")
}