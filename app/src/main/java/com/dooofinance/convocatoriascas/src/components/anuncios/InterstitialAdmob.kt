package com.dooofinance.convocatoriascas.src.components.anuncios

import com.dooofinance.convocatoriascas.MainActivity
import com.dooofinance.convocatoriascas.src.utils.Constants.ENABLE_TEST_APP
import com.dooofinance.convocatoriascas.src.utils.Constants.ID_INTERSTISTIAL_PROD
import com.dooofinance.convocatoriascas.src.utils.Constants.ID_INTERSTISTIAL_TEST
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialAdmob(private val activity: MainActivity) {
    private var interstitialAd: InterstitialAd? = null
    var fullScreenContentCallback: FullScreenContentCallback? = null

    fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            activity,
            if (ENABLE_TEST_APP) ID_INTERSTISTIAL_TEST else ID_INTERSTISTIAL_PROD,
            adRequest,

            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    interstitialAd = null
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    interstitialAd?.fullScreenContentCallback = fullScreenContentCallback
                }
            }
        )
    }

    fun isAdReady(): Boolean {
        return interstitialAd != null
    }

    fun showAd() {
        interstitialAd?.show(activity)
    }
}