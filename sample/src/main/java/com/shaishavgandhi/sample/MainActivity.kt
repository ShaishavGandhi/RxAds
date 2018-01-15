package com.shaishavgandhi.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.NativeAppInstallAd
import com.shaishavgandhi.rxads.RxAdLoader
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RxAdLoader(this, "")
                .loadInstallAd(AdRequest.Builder().build())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<NativeAppInstallAd>() {

                    override fun onSuccess(t: NativeAppInstallAd) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(e: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                })
    }
}
