package com.shaishavgandhi.rxads

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.formats.NativeAppInstallAd
import com.google.android.gms.ads.formats.NativeCustomTemplateAd
import com.shaishavgandhi.rxads.error.AdRequestErrorException
import io.reactivex.Observable
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

    fun loadInstallAd(adRequest: AdRequest, count: Int): Observable<NativeAppInstallAd> {
        return Observable.create { emitter ->
            this.forAppInstallAd { nativeAppInstallAd ->
                emitter.onNext(nativeAppInstallAd)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.onError(AdRequestErrorException(errorCode))
                }
            }).build().loadAds(adRequest, count)
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

    fun loadNativeCustomTemplateAd(template: String, adRequest: AdRequest, count: Int): Observable<NativeCustomTemplateAd> {
        return Observable.create { emitter ->
            this.forCustomTemplateAd(template, {
                emitter.onNext(it)
            }, { _, _ -> }).withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.onError(AdRequestErrorException(errorCode))
                }
            }).build().loadAds(adRequest, count)
        }
    }



}