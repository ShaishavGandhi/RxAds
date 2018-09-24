package com.shaishavgandhi.rxads

import android.content.Context
import androidx.annotation.MainThread
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.shaishavgandhi.rxads.error.AdRequestError
import com.shaishavgandhi.rxads.error.AdRequestErrorException
import io.reactivex.Single

/**
 * Created by shaishav.gandhi on 1/18/18.
 */
class RxInterstitialAd(private val context: Context) {

    /**
     * Load an interstitial ad with the AdLoader
     * that is provided. Should be called from
     * the main thread
     *
     * @param adUnitId ad unit
     * @param adRequest AdRequest
     *
     * @return Single<InterstitialAd>
     */
    @MainThread fun loadAd(adUnitId : String, adRequest: AdRequest): Single<InterstitialAd> {
        return Single.create { emitter ->
            val interstitialAd = InterstitialAd(context)
            interstitialAd.adUnitId = adUnitId

            interstitialAd.adListener = object: AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    emitter.onSuccess(interstitialAd)
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }
            interstitialAd.loadAd(adRequest)
        }
    }

}