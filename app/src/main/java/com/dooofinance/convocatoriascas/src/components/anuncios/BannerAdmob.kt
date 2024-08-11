package com.dooofinance.convocatoriascas.src.components.anuncios

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.dooofinance.convocatoriascas.src.utils.Constants.ENABLE_TEST_APP
import com.dooofinance.convocatoriascas.src.utils.Constants.ID_BANNER_PROD
import com.dooofinance.convocatoriascas.src.utils.Constants.ID_BANNER_TEST
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAdmob() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(
                        AdSize.getInlineAdaptiveBannerAdSize(
                            AdSize.FULL_WIDTH,
                            AdSize.AUTO_HEIGHT
                        )
                    )
                    adUnitId = if (ENABLE_TEST_APP) ID_BANNER_TEST else ID_BANNER_PROD
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }

}
