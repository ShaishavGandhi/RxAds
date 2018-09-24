package com.shaishavgandhi.rxads.extensions

import androidx.annotation.MainThread
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.shaishavgandhi.rxads.error.AdRequestError
import com.shaishavgandhi.rxads.error.AdRequestErrorException
import io.reactivex.Single

/**
 * Created by shaishav.gandhi on 1/19/18.
 */

@MainThread fun InterstitialAd.asSingle(adRequest: AdRequest): Single<InterstitialAd> = Single.create{ emitter ->
    this.adListener = object : AdListener() {
        override fun onAdLoaded() {
            super.onAdLoaded()
            emitter.onSuccess(this@asSingle)
        }

        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }
    this.loadAd(adRequest)
}