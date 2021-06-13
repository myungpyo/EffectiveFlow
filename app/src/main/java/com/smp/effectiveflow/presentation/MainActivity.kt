package com.smp.effectiveflow.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smp.effectiveflow.R
import com.smp.effectiveflow.databinding.ActivityMainBinding
import com.smp.effectiveflow.deviceupdate.domain.DeviceUpdateRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, MainFragment(), null)
                    .commitAllowingStateLoss()
        }

    }

}