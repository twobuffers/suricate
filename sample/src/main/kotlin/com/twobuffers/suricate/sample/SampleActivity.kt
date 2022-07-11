package com.twobuffers.suricate.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.twobuffers.suricate.Config
import com.twobuffers.suricate.Suricate
import com.twobuffers.suricate.sample.databinding.ActivitySampleBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SampleActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showInternetStatus()
    }

    private fun showInternetStatus() {
        Suricate.init(Config(applicationContext))
        Suricate.status
            .onEach { binding.statusValue.text = "$it" }
            .launchIn(lifecycleScope)
        Suricate.connected
            .onEach { binding.connectedValue.text = "$it" }
            .launchIn(lifecycleScope)
    }
}
