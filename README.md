# RxAds
RxJava wrapper for Play Services Ads. The library currently supports loading Native Ads (Native Content, Native Install, Native Custom Template) and Interstitial Ads.

##Philisophy
RxAds' only aim is to make _ad loading_ reactive. The library does not provide ways to convert clicks to ads as streams or to provide various streams for it's varied callback. 

However we do want all functionality to be present so that you can attach your own listeners etc. 

Hence, classes in this library extend their corresponding Play Services Ads class and only add reactive functionality for _ad loading_.


For example:
```kotlin
AdLoader.Builder().withNativeAdOptions(...)
```
can easily become
```kotlin
RxAdLoader(context, "ad_unit_id")
  .withNativeAdOptions(...)
  .loadInstallAd(AdRequest.Builder().build())
  .subscribe(...)
```
You can keep chaining your custom listeners and just call `loadAd(args...)` when you're done.

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

##Download


```groovy
implementation 'com.shaishavgandhi.rxads:rxads:0.1.0'
```

##License
    
    Copyright 2018 Shaishav Gandhi.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


