# RxAds
RxJava wrapper for Play Services Ads

## Usage

#### Native Install Ad
```java
RxAdLoader adLoader = new RxAdLoader(this, "ad_unit_id");
adLoader.loadInstallAd(new AdRequest.Builder().build())
        .subscribeWith(new DisposableSingleObserver<NativeAppInstallAd>() {
            @Override
            public void onSuccess(NativeAppInstallAd nativeAppInstallAd) {
                // Set to view
            }

            @Override
            public void onError(Throwable e) {
                // Handle error
            }
         });
```
#### Native Content Ad
```java
RxAdLoader adLoader = new RxAdLoader(this, "ad_unit_id");
adLoader.loadNativeContentAd(new AdRequest.Builder().build())
        .subscribeWith(new DisposableSingleObserver<NativeContentAd>() {
            @Override
            public void onSuccess(NativeContentAd nativeContentAd) {
                // Set to view                
            }

            @Override
            public void onError(Throwable e) {
                // Handle error
            }
        });
```
#### Interstitial Ad
Java
```java

RxInterstitialAd ad = new RxInterstitialAd(this);
ad.loadAd("ad_unit_id", new AdRequest.Builder().build())
        .subscribeWith(new DisposableSingleObserver<InterstitialAd>() {
            @Override
            public void onSuccess(InterstitialAd interstitialAd) {
                interstitialAd.show();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
```
OR

Use Kotlin extension function
```kotlin
val ad = InterstitialAd(this)
ad.adUnitId = "ad_unit_id"
ad.asSingle(AdRequest.Builder().build())
        .subscribeWith(object : DisposableSingleObserver<InterstitialAd>() {
            override fun onError(e: Throwable) {

            }

            override fun onSuccess(interstitialAd: InterstitialAd) {
                interstitialAd.show()
            }

        })
```