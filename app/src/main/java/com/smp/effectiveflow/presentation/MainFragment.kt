package com.smp.effectiveflow.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.smp.effectiveflow.airquality.domain.AirQuality
import com.smp.effectiveflow.airquality.domain.AirQualityRepository
import com.smp.effectiveflow.databinding.FragmentMainBinding
import com.smp.effectiveflow.deviceupdate.domain.DeviceUpdateRepository
import com.smp.effectiveflow.deviceupdate.domain.UpdateDownloadEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var airQualityRepository: AirQualityRepository

    @Inject
    lateinit var deviceUpdateRepository: DeviceUpdateRepository

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observeAirQualityInOldWay()
//        observeAirQualityInNewWay1()
        observeAirQualityInNewWay2()

        downloadSystemUpdate()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun observeAirQualityInOldWay() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            airQualityRepository
                    //.observeAirQuality1()
                    .observeAirQuality2()
                    .collect { airQuality ->
                        Log.d(TAG, "$airQuality")
                        bindAirQuality(airQuality)
                    }
        }
    }

    private fun observeAirQualityInNewWay1() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                airQualityRepository
                        .observeAirQuality2()
                        .collect { airQuality ->
                            Log.d(TAG, "$airQuality")
                            bindAirQuality(airQuality)
                        }
            }
        }
    }

    private fun observeAirQualityInNewWay2() {
        viewLifecycleOwner.lifecycleScope.launch {
            airQualityRepository
                    .observeAirQuality2()
                    .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect { airQuality ->
                        Log.d(TAG, "$airQuality")
                        bindAirQuality(airQuality)
                    }
        }
    }

    private fun downloadSystemUpdate() {
        viewLifecycleOwner.lifecycleScope.launch {
            deviceUpdateRepository
                    .downloadRecentBinary()
                    .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect { updateDownloadEvent ->
                        Log.d(TAG, "$updateDownloadEvent")
                        bindSystemUpdateEvent(updateDownloadEvent)
                    }
        }
    }

    private fun bindAirQuality(airQuality: AirQuality) {
        binding.pm25View.text = "${airQuality.pm2_5}"
        binding.pm10View.text = "${airQuality.pm10}"
    }

    private fun bindSystemUpdateEvent(updateDownloadEvent: UpdateDownloadEvent) {
        when (updateDownloadEvent) {
            is UpdateDownloadEvent.Started -> {}
            is UpdateDownloadEvent.InProgress -> {
                binding.updateProgressView.progress = updateDownloadEvent.percentage
            }
            is UpdateDownloadEvent.Complete -> {}
            is UpdateDownloadEvent.CompleteWithError -> {}
        }

    }

    private

    companion object {
        const val TAG = "MainFragment"
    }
}