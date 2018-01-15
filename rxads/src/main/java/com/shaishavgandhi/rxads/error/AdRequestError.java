package com.shaishavgandhi.rxads.error;

import android.support.annotation.IntDef;

import com.google.android.gms.ads.AdRequest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by shaishav.gandhi on 1/13/18.
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({AdRequest.ERROR_CODE_INTERNAL_ERROR, AdRequest.ERROR_CODE_INVALID_REQUEST, AdRequest.ERROR_CODE_NETWORK_ERROR, AdRequest.ERROR_CODE_NO_FILL})
public @interface AdRequestError {}
