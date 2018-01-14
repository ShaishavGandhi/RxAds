package com.shaishavgandhi.sample

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.formats.NativeAppInstallAd
import com.google.android.gms.ads.formats.NativeCustomTemplateAd
import com.shaishavgandhi.sample.error.AdRequestErrorException
import io.reactivex.Single

/**
 * Created by shaishav.gandhi on 1/13/18.
 */
class RxAdLoader(context: Context, adUnitId: String) : AdLoader.Builder(context, adUnitId) {

    fun loadInstallAd(adRequest: PublisherAdRequest): Single<NativeAppInstallAd> {
        return Single.create { emitter ->
            this.forAppInstallAd { nativeAppInstallAd ->
                emitter.onSuccess(nativeAppInstallAd)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.onError(AdRequestErrorException(errorCode))
                }
            }).build().loadAd(adRequest)
        }
    }

    fun loadInstallAd(adRequest: AdRequest): Single<NativeAppInstallAd> {
        return Single.create { emitter ->
            this.forAppInstallAd { nativeAppInstallAd ->
                emitter.onSuccess(nativeAppInstallAd)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.onError(AdRequestErrorException(errorCode))
                }
            }).build().loadAd(adRequest)
        }
    }

    fun loadNativeCustomTemplateAd(template: String, adRequest: AdRequest): Single<NativeCustomTemplateAd> {
        return Single.create { emitter ->
            this.forCustomTemplateAd(template, {
                emitter.onSuccess(it)
            }, { _, _ -> }).withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.onError(AdRequestErrorException(errorCode))
                }
            }).build().loadAd(adRequest)
        }
    }

    fun loadNativeCustomTemplateAd(template: String, adRequest: PublisherAdRequest): Single<NativeCustomTemplateAd> {
        return Single.create { emitter ->
            this.forCustomTemplateAd(template, {
                emitter.onSuccess(it)
            }, { _, _ -> }).withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.onError(AdRequestErrorException(errorCode))
                }
            }).build().loadAd(adRequest)
        }
    }



}