package com.shaishavgandhi.rxads

import android.content.Context
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

/**
 * Created by shaishav.gandhi on 1/15/18.
 */
@RunWith(JUnit4::class)
class RxAdLoaderTest {

    lateinit var adLoader: RxAdLoader
    @Mock lateinit var context: Context

    @Before @Throws fun setUp() {
        adLoader = RxAdLoader(context, "")
    }

}