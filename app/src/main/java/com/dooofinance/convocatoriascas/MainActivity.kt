package com.dooofinance.convocatoriascas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dooofinance.convocatoriascas.navigation.AppNavigation
import com.dooofinance.convocatoriascas.src.components.anuncios.InterstitialAdmob
import com.dooofinance.convocatoriascas.src.function.FeedbackDialog
import com.dooofinance.convocatoriascas.src.utils.LogoColorPrimary
import com.dooofinance.convocatoriascas.ui.theme.ConvocatoriasCasTheme
import com.dooofinance.convocatoriascas.ui.viewModel.AdmobViewModel
import com.dooofinance.convocatoriascas.ui.viewModel.ConvocatoriaViewModel
import com.google.android.gms.ads.MobileAds
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var adManager: InterstitialAdmob
    private lateinit var adViewModel: AdmobViewModel
    private val convocatoriaViewModel: ConvocatoriaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adManager = InterstitialAdmob(this)
        adViewModel = AdmobViewModel(adManager)

        enableEdgeToEdge()

        //Feedback init.
        FeedbackDialog(this@MainActivity)

        //Admob init.
        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            MobileAds.initialize(this@MainActivity) {}
        }

        setContent {
            ConvocatoriasCasTheme {

                Scaffold { paddingValues ->
                    AppNavigation(
                        convocatoriaViewModel = convocatoriaViewModel,
                        admobViewModel = adViewModel,
                        modifier = Modifier
                            .padding(top = paddingValues.calculateTopPadding(),
                                bottom = paddingValues.calculateBottomPadding())
                    )
                }
            }
        }
    }
}



