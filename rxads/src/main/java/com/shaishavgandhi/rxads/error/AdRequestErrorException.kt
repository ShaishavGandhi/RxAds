package com.shaishavgandhi.rxads.error;

/**
 * Created by shaishav.gandhi on 1/13/18.
 */

class AdRequestErrorException(adRequestError: AdRequestError) : Exception(adRequestError.getMessage())
