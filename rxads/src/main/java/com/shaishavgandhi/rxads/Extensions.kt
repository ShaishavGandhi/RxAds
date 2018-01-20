package com.shaishavgandhi.rxads

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.shaishavgandhi.rxads.error.AdRequestErrorException
import io.reactivex.Single

/**
 * Created by shaishav.gandhi on 1/19/18.
 */

@SuppressLint("MissingPermission")
inline fun InterstitialAd.asSingle(adRequest: AdRequest): Single<InterstitialAd> = Single.create { emitter ->
    this.adListener = object : AdListener() {
        override fun onAdLoaded() {
            super.onAdLoaded()
            emitter.onSuccess(this@asSingle)
        }

        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.onError(AdRequestErrorException(errorCode))
        }
    }
    this.loadAd(adRequest)
}