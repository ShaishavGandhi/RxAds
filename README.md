RxAds
========

![Travis](https://img.shields.io/travis/shaishavgandhi05/RxAds.svg)

RxJava wrapper for Play Services Ads. The library currently supports loading Native Ads (Native Content, Native Install, Native Custom Template) and Interstitial Ads.

## Philosophy
RxAds' only aim is to make _ad loading_ reactive. The library does not provide ways to convert clicks to ads as streams or to provide various streams for it's varied callback. 

However, Play Services Ads is a big SDK and it's not the aim of this library to replicate all functionality. Hence, RxAds builds on existing functionality and allows you to load ads using reactive streams. 


For example:
```java
AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
    .forContentAd(new OnContentAdLoadedListener() {
        @Override
        public void onContentAdLoaded(NativeContentAd contentAd) {
            // Show the content ad.
        }
    })
    .withAdListener(new AdListener() {
        @Override
        public void onAdFailedToLoad(int errorCode) {
            // Handle the failure by logging, altering the UI, and so on.
        }
    })
    .withNativeAdOptions(new NativeAdOptions.Builder()
            // Methods in the NativeAdOptions.Builder class can be
            // used here to specify individual options settings.
            .build())
    .build();

adLoader.loadAd(new PublisherAdRequest.Builder().build());
```
can easily become
```java
Disposable disposable = new RxAdLoader(context, "ad_unit_id") // Initialize same as AdLoader.Builder
                // Chain any other options from Play Services Ads
                .withNativeAdOptions(new NativeAdOptions.Builder().build())
                .loadNativeContentAd(new AdRequest.Builder().build()) // Load ad
                .subscribe(...); 
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
## Kotlin Extensions

The library is written in Kotlin and it has Kotlin extensions for most APIs

#### Interstitial Ad
```kotlin
val ad = InterstitialAd(this)
ad.adUnitId = "ad_unit_id"
ad.asSingle(AdRequest.Builder().build())
        .subscribe({ interstitialAd -> 
            interstitialAd.show()
        }, { throwable -> 
            // Handle error
        })
```

#### Native Install Ad
```kotlin
AdLoader.Builder(this, "ad_unit_id")
                .loadInstallAd(AdRequest.Builder().build())
                .subscribe({ installAd ->
                    // Set to view
                    }, { e ->
                    // Handle error
                })
```

## Error Handling

If you've used the Play Services Ads SDK, the error handling leaves much to be desired. It returns an error code, which you must then search for. RxAds makes it easy and generates a human readable error message actually telling you what happened. 

## Download

[![Maven Central](https://img.shields.io/maven-central/v/com.shaishavgandhi.rxads/rxads.svg)](https://mvnrepository.com/artifact/com.shaishavgandhi.rxads/rxads)
```groovy
implementation 'com.shaishavgandhi.rxads:rxads:x.y.z'
```
Before Play Services 15.0, projects importing multiple Play Services library had to have the same version number to avoid runtime crashes. If you haven't yet migrated to 15.0 or above, you can compile your project by excluding `com.google.android.gms`from this library as such:

```groovy
implementation ('com.shaishavgandhi.rxads:rxads:x.y.z') {
    exclude group: 'com.google.android.gms'
}
```
After the Play Services 15 release, this is not a problem. RxAds will have a corresponding release for every Play Services Ads release to give you the latest functionality. 

Snapshots of the development version are available in [Sonatype's snapshots repository.](https://oss.sonatype.org/content/repositories/snapshots/)

## Contributing

Contributions are welcome! We're always looking to expand the usecases and would love to accept PRs.

If you would like to contribute code you can do so through GitHub by forking
the repository and sending a pull request.

Make sure your code compiles by running `./gradlew clean build`.

## License
    
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


