package com.shaishavgandhi.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.formats.NativeAppInstallAd
import com.google.android.gms.ads.formats.NativeContentAd
import com.shaishavgandhi.rxads.RxAdLoader
import com.shaishavgandhi.rxads.RxInterstitialAd
import com.shaishavgandhi.rxads.asSingle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var headlineView: TextView
    lateinit var imageView: ImageView
    lateinit var nativeInstallButton: Button
    lateinit var nativeContentButton: Button
    lateinit var interstitalButton: Button

    var disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headlineView = findViewById(R.id.headline)
        imageView = findViewById(R.id.image)
        nativeInstallButton = findViewById(R.id.installAd)
        nativeContentButton = findViewById(R.id.contentAd)
        interstitalButton = findViewById(R.id.interstitialAd)


        nativeInstallButton.setOnClickListener {
            loadNativeInstallAd()
        }

        nativeContentButton.setOnClickListener {
            loadNativeContentAd()
        }

        interstitalButton.setOnClickListener {
            loadInterstitialAd()
        }

    }

    private fun loadNativeInstallAd() {
        val disposable = RxAdLoader(this, "ca-app-pub-3940256099942544/2247696110")
                .loadInstallAd(AdRequest.Builder().build())
                .subscribeWith(object : DisposableSingleObserver<NativeAppInstallAd>() {

                    override fun onSuccess(installAd: NativeAppInstallAd) {
                        headlineView.text = installAd.headline
                        Glide.with(imageView).load(installAd.images[0].uri).into(imageView)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })

        disposables.add(disposable)
    }

    private fun loadNativeContentAd() {
        val disposable = RxAdLoader(this, "ca-app-pub-3940256099942544/2247696110")
                .loadNativeContentAd(AdRequest.Builder().build())
                .subscribeWith(object : DisposableSingleObserver<NativeContentAd>() {
                    override fun onSuccess(contentAd: NativeContentAd) {
                        headlineView.text = contentAd.headline
                        Glide.with(imageView).load(contentAd.images[0].uri).into(imageView)
                    }

                    override fun onError(e: Throwable) {
                    }

                })

        disposables.add(disposable)
    }

    private fun loadInterstitialAd() {
        RxInterstitialAd(this)
                .loadAd("ca-app-pub-3940256099942544/1033173712", AdRequest.Builder().build())
                .subscribeWith(object : DisposableSingleObserver<InterstitialAd>() {
                    override fun onError(e: Throwable) {

                    }

                    override fun onSuccess(interstitialAd: InterstitialAd) {
                        interstitialAd.show()
                    }

                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}