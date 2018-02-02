package com.shaishavgandhi.rxads.error

import com.google.android.gms.ads.AdRequest

/**
 * Created by shaishav.gandhi on 2/1/18.
 */
class AdRequestError(val adRequestError: Int) {

    fun getMessage(): String {
        when(adRequestError) {
            AdRequest.ERROR_CODE_INTERNAL_ERROR ->
                    return "Couldn't load ad. Something happened internally; for instance, an invalid response was " +
                            "received from the ad server. Error code : ${AdRequest.ERROR_CODE_INTERNAL_ERROR}"
            AdRequest.ERROR_CODE_INVALID_REQUEST ->
                    return "Couldn't load ad. The ad request was invalid; for instance, the ad unit ID " +
                            "was incorrect. Error code : ${AdRequest.ERROR_CODE_INVALID_REQUEST}"
            AdRequest.ERROR_CODE_NETWORK_ERROR ->
                    return "Couldn't load ad. The ad request was unsuccessful due to network " +
                            "connectivity. Error code : ${AdRequest.ERROR_CODE_NETWORK_ERROR}"
            AdRequest.ERROR_CODE_NO_FILL ->
                    return "Couldn't load ad. The ad request was successful, but no ad was " +
                            "returned due to lack of ad inventory.. Error code : ${AdRequest.ERROR_CODE_NO_FILL}"
            else ->
                    return "Couldn't load ad"
        }
    }

}