RxAds
========

![Travis](https://img.shields.io/travis/shaishavgandhi05/RxAds.svg)

RxJava wrapper for Play Services Ads. The library currently supports loading Native Ads (Native Content, Native Install, Native Custom Template) and Interstitial Ads.

## Philosophy
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


