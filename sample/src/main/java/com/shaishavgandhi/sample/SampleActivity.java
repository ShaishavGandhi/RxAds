package com.shaishavgandhi.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.shaishavgandhi.rxads.RxAdLoader;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RxAdLoader adLoader = new RxAdLoader(this, "");

        adLoader.loadInstallAd(new AdRequest.Builder().build())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<NativeAppInstallAd>() {
                    @Override
                    public void accept(NativeAppInstallAd nativeAppInstallAd) throws Exception {
                    }
                });

    }

}
