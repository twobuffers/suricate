package com.twobuffers.suricate

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.HttpURLConnection
import java.net.InetAddress
import java.net.SocketTimeoutException
import java.net.URL
import java.util.concurrent.TimeUnit.MILLISECONDS

fun checkInternetWithDnsLookup(hostname: String = "google.com"): Boolean =
    try {
        val ipAddr = InetAddress.getByName(hostname)
        !ipAddr.equals("")
    } catch (e: Exception) {
        false
    }

// When tested on V2 without HTTP Toolkit intercepting it returned false.
// When I started intercepting traffic it worked fine - returned true.
fun checkInternetWithPing(ipAddress: ByteArray = byteArrayOf(8,8,8,8)): Boolean =
    try {
        val ipAddr = InetAddress.getByAddress(ipAddress)
        ipAddr.isReachable(1000)
    } catch (e: Exception) {
        false
    }

// Disadvantage: it fetches the website
fun checkInternetWithHttp200(endpoint: String = "https://www.google.com/") = try {
    val url = URL(endpoint)
    val urlc = (url.openConnection() as HttpURLConnection)
        .apply {
            setRequestProperty("User-Agent", "InternetMonitor/1.0.1")
            setRequestProperty("Connection", "close")
            connectTimeout = 1000
        }
    urlc.connect()
    urlc.responseCode == HttpURLConnection.HTTP_OK
} catch (e: IOException) {
    false
}

// based on 204 endpoints:
// - http://google.com/generate_204
// - https://connectivitycheck.android.com/generate_204
fun checkInternetWithHttp204(
    endpoint: String = "https://google.com/generate_204",
    logger: Logger? = null,
) = try {
    val url = URL(endpoint)
    val time0 = System.currentTimeMillis()
    val urlc: HttpURLConnection = (url.openConnection() as HttpURLConnection)
        .apply {
            setRequestProperty("User-Agent", "test")
            setRequestProperty("Connection", "close")
            connectTimeout = 1000
            readTimeout = 1000
            useCaches = false
            defaultUseCaches = false
        }
    val time1 = System.currentTimeMillis()
    urlc.connect()
    val time2 = System.currentTimeMillis()
    val code = urlc.responseCode
    val time3 = System.currentTimeMillis()
    logger?.log("time0 = %5d".format(time1-time0))
    logger?.log("time1 = %5d".format(time2-time1))
    logger?.log("time2 = %5d".format(time3-time2))
    code == HttpURLConnection.HTTP_NO_CONTENT
} catch (e: IOException) {
    logger?.log("caught: ${e.message}")
    false
} catch (e: Exception) {
    logger?.log("caught: ${e.message}")
    false
}

val defaultClient = OkHttpClient.Builder()
    .connectTimeout(2000, MILLISECONDS)
    .readTimeout(2000, MILLISECONDS)
    .cache(null)
    .build()

fun checkInternetWithHttp204AndOkHttp(
    okHttpClient: OkHttpClient = defaultClient,
    endpoint: String = "https://google.com/generate_204",
    logger: Logger? = null,
): Boolean = try {
    val request = Request.Builder().url(endpoint).build()
    val call = okHttpClient.newCall(request)
    val time1 = System.currentTimeMillis()
    val response = call.execute()
    val time2 = System.currentTimeMillis()
    val code = response.code
    val time3 = System.currentTimeMillis()
    logger?.log("time1 = %5d".format(time2-time1))
    logger?.log("time2 = %5d".format(time3-time2))
    code == 204
} catch (e: SocketTimeoutException) {
    logger?.log("caught: ${e.message}, ${e.bytesTransferred}")
    false
} catch (e: Throwable) {
    logger?.log("caught: ${e.message}")
    false
}
