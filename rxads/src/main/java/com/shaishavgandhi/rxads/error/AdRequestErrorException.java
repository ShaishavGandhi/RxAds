package com.shaishavgandhi.rxads.error;

/**
 * Created by shaishav.gandhi on 1/13/18.
 */

public class AdRequestErrorException extends Exception {

    @AdRequestError private int adRequestError;

    public AdRequestErrorException(@AdRequestError int adRequestError) {
        // TODO: Make this more readable
        super("Error fetching ad. Error code : " + adRequestError);
        this.adRequestError = adRequestError;
    }

}
