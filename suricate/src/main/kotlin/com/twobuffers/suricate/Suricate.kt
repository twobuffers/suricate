package com.twobuffers.suricate

import android.content.Context
import android.util.Log
import com.twobuffers.wire.coroutines.everyFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import java.sql.Time
import java.sql.Timestamp
import java.util.concurrent.TimeUnit.MILLISECONDS

interface Logger {
    fun log(string: String)
}

@OptIn(DelicateCoroutinesApi::class)
data class Config(
    val context: Context,
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    val scope: CoroutineScope = GlobalScope,
    val delayWhenConnectedMs: Long = 5_000,
    val delayWhenDisconnectedMs: Long = 500,
    val timeoutMs: Long = 4_000,
    val logger: Logger? = null,
)

enum class Status { NetworkMissing, InternetMissing, Connected }

object Suricate {
    lateinit var c: Config
    lateinit var okHttpClient: OkHttpClient

    private val _status = MutableSharedFlow<Status>()
    val status: Flow<Status> = _status.distinctUntilChanged()
    val connected: Flow<Boolean> = status.map { it == Status.Connected }

    lateinit var checkingJob: Job

    fun init(config: Config) {
        this.c = config
        this.okHttpClient = OkHttpClient.Builder()
            .connectTimeout(config.timeoutMs, MILLISECONDS)
            .readTimeout(config.timeoutMs, MILLISECONDS)
            .cache(null)
            .build()
        restartChecking()
    }

    private fun restartChecking() {
        if (this::checkingJob.isInitialized && checkingJob.isActive) checkingJob.cancel()
        checkingJob = _status
            .onStart { emit(Status.NetworkMissing) }
            .distinctUntilChanged()
            .onEach { c.logger?.log("flow - status: ${it::class.simpleName}") }
            .map {
                if (it == Status.Connected) c.delayWhenConnectedMs
                else c.delayWhenDisconnectedMs
            }
            .onEach { Log.d("InternetMonitor", "flow - delay: $it") }
            .flatMapLatest { delay ->
                everyFlow(repeatMillis = delay, initialDelay = 0)
                    .onEach {
                        c.logger?.log("flow - check status at: ${Time(Timestamp(it).time)}")
                        check()
                    }
            }
            .launchIn(c.scope)
    }
    // TODO: Handle experimental API warnings

    suspend fun check(): Status = withContext(c.ioDispatcher) {
        val result =
            if (!isNetworkAvailable(c.context)) Status.NetworkMissing
            else if (!checkInternetWithHttp204AndOkHttp(okHttpClient)) Status.InternetMissing
            else Status.Connected
        _status.emit(result)
        c.logger?.log("status: $result")
        return@withContext result
    }
}
