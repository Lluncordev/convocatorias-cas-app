package com.dooofinance.convocatoriascas.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dooofinance.convocatoriascas.src.components.anuncios.InterstitialAdmob
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdmobViewModel(private val adManager: InterstitialAdmob):ViewModel() {
    private val _adViewComplete = MutableStateFlow(false)
    val adViewComplete: StateFlow<Boolean> = _adViewComplete.asStateFlow()

    private val _adReady = MutableStateFlow(false)
    val adReady: StateFlow<Boolean> = _adReady.asStateFlow()

    init {
        loadAd()
        setupFullScreenCallback()
    }

    fun loadAd(){
        adManager.loadAd()
        _adViewComplete.value = false
        checkAdStatus()
    }

    fun showAd() {
        if (adManager.isAdReady()) {
            adManager.showAd()
            _adReady.value = false
        }
    }

    private fun checkAdStatus(){
        viewModelScope.launch {
            while (true){
                _adReady.value = adManager.isAdReady()
                if (_adReady.value) break
                delay(3000)
            }
        }
    }

    private fun setupFullScreenCallback() {
        adManager.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                viewModelScope.launch {
                    _adViewComplete.value = true
                    _adReady.value = false
                }
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                viewModelScope.launch {
                    _adViewComplete.value = true
                    _adReady.value = false
                }
            }
        }
    }
}