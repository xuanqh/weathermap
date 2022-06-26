package com.weathermap.features.setting

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.weathermap.R
import com.weathermap.base.BaseActivity
import com.weathermap.databinding.ActivitySettingBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SettingActivity : BaseActivity<SettingViewModel>() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        title = getString(R.string.setting)
        setEventListener()
    }

    private fun setEventListener() {
        binding.tvTextLarge.setOnClickListener {
            settingTextSize(R.style.Theme_WeatherMap_Large)
        }
        binding.tvTextMedium.setOnClickListener {
            settingTextSize(R.style.Theme_WeatherMap_Medium)
        }
        binding.tvTextSmall.setOnClickListener {
            settingTextSize(R.style.Theme_WeatherMap_Small)
        }
    }

    private fun settingTextSize(themId: Int) {
        viewModel.saveAppTextSize(applicationContext, themId)
        setResult(Activity.RESULT_OK)
        finish()
    }

    override val viewModel: SettingViewModel
        get() = getViewModel()
}