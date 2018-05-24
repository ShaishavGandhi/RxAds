package com.shaishavgandhi.rxads

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.runner.AndroidJUnit4
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.TestObserver
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

/**
 * Created by shaishav.gandhi on 1/29/18.
 */
@RunWith(AndroidJUnit4::class)
class RxInterstitialAdTest {

    companion object {
        val adUnitId = "ca-app-pub-3940256099942544/1033173712"
    }

    lateinit var interstitialAd: RxInterstitialAd

    @Before @Throws fun setUp() {
        interstitialAd = RxInterstitialAd(getTargetContext())
    }

    @Ignore @Test fun loadAdWithSampleAdUnitId() {
        val testObserver = TestObserver<InterstitialAd>()
        interstitialAd.loadAd(adUnitId, AdRequest.Builder().build())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(testObserver)

        testObserver.awaitTerminalEvent(10, TimeUnit.SECONDS)

        testObserver.assertNoErrors()
                .assertValueCount(1)
                .assertValue { !it.isLoading }
    }

    @Ignore @Test fun loadAdWithWrongAdUnitId() {
        val testObserver = TestObserver<InterstitialAd>()
        interstitialAd.loadAd("wrong_ad_unit", AdRequest.Builder().build())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(testObserver)

        testObserver.awaitTerminalEvent(10, TimeUnit.SECONDS)

        testObserver.assertNoValues()
        assertEquals(1, testObserver.errorCount())
    }

}