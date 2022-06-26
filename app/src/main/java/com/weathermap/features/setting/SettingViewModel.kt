package com.weathermap.features.setting

import android.content.Context
import com.weathermap.base.BaseViewModel
import com.weathermap.utils.PrefKeys
import com.weathermap.utils.SharedPrefUtils

class SettingViewModel: BaseViewModel() {
    fun saveAppTextSize(context: Context, themId: Int) {
        SharedPrefUtils.saveData(context, PrefKeys.KEY_THEME_ID, themId)
    }
}