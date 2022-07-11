package com.twobuffers.suricate

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test

class MethodsAndroidTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun call_isNetworkAvailable() = runBlocking {
        val networkAvailable = isNetworkAvailable(context)
        assertThat(networkAvailable).isTrue()
    }

    @Test
    fun call_checkInternetWithDnsLookup() = runBlocking {
        val connected = checkInternetWithDnsLookup()
        assertThat(connected).isTrue()
    }

    @Ignore("On emulator it works, on device not. It is not clear why is that so.")
    @Test
    fun call_checkInternetWithPing() = runBlocking {
        val connected = checkInternetWithPing()
        assertThat(connected).isTrue()
    }

    @Test
    fun call_checkInternetWithHttp200() = runBlocking {
        val connected = checkInternetWithHttp200()
        assertThat(connected).isTrue()
    }

    @Test
    fun call_checkInternetWithHttp204() = runBlocking {
        val connected = checkInternetWithHttp204()
        assertThat(connected).isTrue()
    }

    @Test
    fun call_checkInternetWithHttp204AndOkHttp() = runBlocking {
        val connected = checkInternetWithHttp204AndOkHttp()
        assertThat(connected).isTrue()
    }
}
