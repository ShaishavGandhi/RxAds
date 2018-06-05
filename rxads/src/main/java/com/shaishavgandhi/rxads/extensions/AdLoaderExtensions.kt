package com.shaishavgandhi.rxads.extensions

import android.support.annotation.MainThread
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.formats.NativeAppInstallAd
import com.google.android.gms.ads.formats.NativeContentAd
import com.google.android.gms.ads.formats.NativeCustomTemplateAd
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.shaishavgandhi.rxads.error.AdRequestError
import com.shaishavgandhi.rxads.error.AdRequestErrorException
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Load a native install ad using the provided
 * PublisherAdRequest. Must be called from
 * the main thread
 *
 * @param adRequest PublisherAdRequest
 * @return Single<NativeAppInstallAd>
 */
@MainThread fun AdLoader.Builder.loadInstallAd(adRequest: AdRequest): Single<NativeAppInstallAd> = Single.create { emitter ->
    this.forAppInstallAd { nativeAppInstallAd ->
        emitter.onSuccess(nativeAppInstallAd)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAd(adRequest)
}

/**
 * Load a native install ad with the provided
 * AdRequest. Must be called from main thread
 *
 * @param adRequest AdRequest
 * @return Single<NativeAppInstallAd>
 */
@MainThread fun AdLoader.Builder.loadInstallAd(adRequest: PublisherAdRequest): Single<NativeAppInstallAd> = Single.create { emitter ->
    this.forAppInstallAd { nativeAppInstallAd ->
        emitter.onSuccess(nativeAppInstallAd)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAd(adRequest)
}

/**
 * Load multiple native install ads with
 * provided AdRequest. Called from
 * main thread
 *
 * @param adRequest AdRequest
 * @param count number of ads to load
 * @return Observable<NativeAppInstallAd>
 */
@MainThread fun AdLoader.Builder.loadInstallAds(adRequest: AdRequest, count: Int): Observable<NativeAppInstallAd> = Observable.create { emitter ->
    this.forAppInstallAd { nativeAppInstallAd ->
        emitter.onNext(nativeAppInstallAd)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAds(adRequest, count)
}

/**
 * Load native custom template ad using provided
 * templateId and adRequest. Call from main thread
 *
 * @param template templateId
 * @param adRequest AdRequest
 *
 * @return Single<NativeCustomTemplateAd>
 */
@MainThread fun AdLoader.Builder.loadNativeCustomTemplateAd(template: String, adRequest: AdRequest): Single<NativeCustomTemplateAd> = Single.create { emitter ->
    this.forCustomTemplateAd(template, {
        emitter.onSuccess(it)
    }, { _, _ -> }).withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAd(adRequest)
}

/**
 * Load native custom template ad using provided
 * template and publisherAdRequest. Call from
 * main thread
 *
 * @param template templateId
 * @param adRequest PublisherAdRequest
 *
 * @return Single<NativeCustomTemplateAd>
 */
@MainThread fun AdLoader.Builder.loadNativeCustomTemplateAd(template: String, adRequest: PublisherAdRequest): Single<NativeCustomTemplateAd> = Single.create { emitter ->
    this.forCustomTemplateAd(template, {
        emitter.onSuccess(it)
    }, { _, _ -> }).withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAd(adRequest)
}

/**
 * Load multiple native custom template ad using
 * provided template, adRequest and count. Call
 * from main thread
 *
 * @param template templateId
 * @param adRequest AdRequest
 * @param count number of ads to call
 * @return Observable<NativeCustomTemplateAd>
 */
@MainThread fun AdLoader.Builder.loadNativeCustomTemplateAds(template: String, adRequest: AdRequest, count: Int): Observable<NativeCustomTemplateAd> = Observable.create { emitter ->
    this.forCustomTemplateAd(template, {
        emitter.onNext(it)
    }, { _, _ -> }).withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAds(adRequest, count)
}

/**
 * Load native content ad with given publisherAdRequest
 * Call from main thread
 *
 * @param adRequest PublisherAdRequest
 * @return Single<NativeContentAd>
 */
@MainThread fun AdLoader.Builder.loadNativeContentAd(adRequest: PublisherAdRequest): Single<NativeContentAd> = Single.create { emitter ->
    this.forContentAd { ad ->
        emitter.onSuccess(ad)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAd(adRequest)
}

/**
 * Load native content ad given the adRequest.
 * Must be called from the main thread
 *
 * @param adRequest AdRequest
 * @return Single<NativeContentAd>
 */
@MainThread fun AdLoader.Builder.loadNativeContentAd(adRequest: AdRequest): Single<NativeContentAd> = Single.create { emitter ->
    this.forContentAd { ad ->
        emitter.onSuccess(ad)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAd(adRequest)
}

/**
 * Load multiple native content ads given the
 * adRequest and count of ads
 *
 * @param adRequest AdRequest
 * @param count number of ads to load
 *
 * @return Observable<NativeContentAd>
 */
@MainThread fun AdLoader.Builder.loadNativeContentAds(adRequest: AdRequest, count: Int): Observable<NativeContentAd> = Observable.create { emitter ->
    this.forContentAd { ad ->
        emitter.onNext(ad)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAds(adRequest, count)
}

/**
 * Load unified content ad given the adRequest.
 * Should be called from the main thread
 *
 * @param adRequest AdRequest
 * @return Single<UnifiedNativeAd>
 */
@MainThread fun AdLoader.Builder.loadUnifiedAd(adRequest: AdRequest): Single<UnifiedNativeAd> = Single.create { emitter ->
    this.forUnifiedNativeAd { ad ->
        emitter.onSuccess(ad)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAd(adRequest)
}

/**
 * Load unified content ad given the adRequest.
 * Should be called from the main thread
 *
 * @param adRequest PublisherAdRequest
 * @return Single<UnifiedNativeAd>
 */
@MainThread fun AdLoader.Builder.loadUnifiedAd(adRequest: PublisherAdRequest): Single<UnifiedNativeAd> = Single.create { emitter ->
    this.forUnifiedNativeAd { ad ->
        emitter.onSuccess(ad)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAd(adRequest)
}

/**
 * Load multiple unified content ads given an adRequest
 * Should be called from the main thread
 *
 * @param adRequest
 * @param count
 * @return Observable<UnifiedNativeAd>
 */
@MainThread fun AdLoader.Builder.loadUnifiedAds(adRequest: AdRequest, count: Int): Observable<UnifiedNativeAd> = Observable.create { emitter ->
    this.forUnifiedNativeAd { ad ->
        emitter.onNext(ad)
    }.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
        }
    }).build().loadAds(adRequest, count)
}