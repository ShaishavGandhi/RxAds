package com.shaishavgandhi.rxads

import android.content.Context
import androidx.annotation.MainThread
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.formats.*
import com.shaishavgandhi.rxads.error.AdRequestError
import com.shaishavgandhi.rxads.error.AdRequestErrorException
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by shaishav.gandhi on 1/13/18.
 */
class RxAdLoader(context: Context, adUnitId: String) : AdLoader.Builder(context, adUnitId) {

    override fun withNativeAdOptions(options: NativeAdOptions?): RxAdLoader{
        super.withNativeAdOptions(options)
        return this
    }

    /**
     * Load a native install ad using the provided
     * PublisherAdRequest. Must be called from
     * the main thread
     *
     * @param adRequest PublisherAdRequest
     * @return Single<NativeAppInstallAd>
     */
    @MainThread fun loadInstallAd(adRequest: PublisherAdRequest): Single<NativeAppInstallAd> {
        return Single.create { emitter ->
            this.forAppInstallAd { nativeAppInstallAd ->
                emitter.onSuccess(nativeAppInstallAd)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAd(adRequest)
        }
    }

    /**
     * Load a native install ad with the provided
     * AdRequest. Must be called from main thread
     *
     * @param adRequest AdRequest
     * @return Single<NativeAppInstallAd>
     */
    @MainThread fun loadInstallAd(adRequest: AdRequest): Single<NativeAppInstallAd> {
        return Single.create { emitter ->
            this.forAppInstallAd { nativeAppInstallAd ->
                emitter.onSuccess(nativeAppInstallAd)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAd(adRequest)
        }
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
    @MainThread fun loadInstallAds(adRequest: AdRequest, count: Int): Observable<NativeAppInstallAd> {
        return Observable.create { emitter ->
            this.forAppInstallAd { nativeAppInstallAd ->
                emitter.onNext(nativeAppInstallAd)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAds(adRequest, count)
        }
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
    @MainThread fun loadNativeCustomTemplateAd(template: String, adRequest: AdRequest): Single<NativeCustomTemplateAd> {
        return Single.create { emitter ->
            this.forCustomTemplateAd(template, {
                emitter.onSuccess(it)
            }, { _, _ -> }).withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAd(adRequest)
        }
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
    @MainThread fun loadNativeCustomTemplateAd(template: String, adRequest: PublisherAdRequest): Single<NativeCustomTemplateAd> {
        return Single.create { emitter ->
            this.forCustomTemplateAd(template, {
                emitter.onSuccess(it)
            }, { _, _ -> }).withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAd(adRequest)
        }
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
    @MainThread fun loadNativeCustomTemplateAds(template: String, adRequest: AdRequest, count: Int): Observable<NativeCustomTemplateAd> {
        return Observable.create { emitter ->
            this.forCustomTemplateAd(template, {
                emitter.onNext(it)
            }, { _, _ -> }).withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAds(adRequest, count)
        }
    }

    /**
     * Load native content ad given the adRequest.
     * Must be called from the main thread
     *
     * @param adRequest AdRequest
     * @return Single<NativeContentAd>
     */
    @MainThread fun loadNativeContentAd(adRequest: AdRequest): Single<NativeContentAd> {
        return Single.create { emitter ->
            this.forContentAd { ad ->
                emitter.onSuccess(ad)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAd(adRequest)
        }
    }

    /**
     * Load native content ad with given publisherAdRequest
     * Call from main thread
     *
     * @param adRequest PublisherAdRequest
     * @return Single<NativeContentAd>
     */
    @MainThread fun loadNativeContentAd(adRequest: PublisherAdRequest): Single<NativeContentAd> {
        return Single.create { emitter ->
            this.forContentAd { ad ->
                emitter.onSuccess(ad)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAd(adRequest)
        }
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
    @MainThread fun loadNativeContentAds(adRequest: AdRequest, count: Int): Observable<NativeContentAd> {
        return Observable.create { emitter ->
            this.forContentAd { ad ->
                emitter.onNext(ad)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAds(adRequest, count)
        }
    }

    /**
     * Load unified content ad given the adRequest.
     * Should be called from the main thread
     *
     * @param adRequest AdRequest
     * @return Single<UnifiedNativeAd>
     */
    @MainThread fun loadUnifiedAd(adRequest: AdRequest): Single<UnifiedNativeAd> {
        return Single.create { emitter ->
            this.forUnifiedNativeAd { ad ->
                emitter.onSuccess(ad)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAd(adRequest)
        }
    }

    /**
     * Load unified content ad given the adRequest.
     * Should be called from the main thread
     *
     * @param adRequest PublisherAdRequest
     * @return Single<UnifiedNativeAd>
     */
    @MainThread fun loadUnifiedAd(adRequest: PublisherAdRequest): Single<UnifiedNativeAd> {
        return Single.create { emitter ->
            this.forUnifiedNativeAd { ad ->
                emitter.onSuccess(ad)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAd(adRequest)
        }
    }

    /**
     * Load multiple unified content ads given an adRequest
     * Should be called from the main thread
     *
     * @param adRequest
     * @param count
     * @return Observable<UnifiedNativeAd>
     */
    @MainThread fun loadUnifiedAds(adRequest: AdRequest, count: Int): Observable<UnifiedNativeAd> {
        return Observable.create { emitter ->
            this.forUnifiedNativeAd { ad ->
                emitter.onNext(ad)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    emitter.tryOnError(AdRequestErrorException(AdRequestError(errorCode)))
                }
            }).build().loadAds(adRequest, count)
        }
    }
}