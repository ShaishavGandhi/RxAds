package com.shaishavgandhi.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.NativeAppInstallAd
import com.shaishavgandhi.rxads.RxAdLoader
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var headlineView: TextView
    lateinit var imageView: ImageView
    lateinit var nativeInstallButton: Button

    var disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headlineView = findViewById(R.id.headline)
        imageView = findViewById(R.id.image)
        nativeInstallButton = findViewById(R.id.installAd)


        nativeInstallButton.setOnClickListener {
            loadAd()
        }

    }

    private fun loadAd() {
        RxAdLoader(this, "ca-app-pub-3940256099942544/2247696110")
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
    }
}