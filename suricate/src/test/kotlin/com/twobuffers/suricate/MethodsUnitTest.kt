package com.twobuffers.suricate

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test

class MethodsUnitTest {

    @Test
    fun call_checkInternetWithDnsLookup() = runBlocking {
        val connected = checkInternetWithDnsLookup()
        assertThat(connected).isTrue()
    }

    @Ignore("Ping for some reason doesn't work")
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
