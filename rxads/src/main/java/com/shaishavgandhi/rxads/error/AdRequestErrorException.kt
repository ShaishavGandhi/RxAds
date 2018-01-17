package com.shaishavgandhi.rxads.error;

import com.google.android.gms.ads.AdRequest

/**
 * Created by shaishav.gandhi on 1/13/18.
 */

class AdRequestErrorException(@AdRequestError adRequestError: Int) : Exception() {

}
