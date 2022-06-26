package com.weathermap.features.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.weathermap.R
import com.weathermap.base.BaseActivity
import com.weathermap.data.models.CityWeatherModel
import com.weathermap.databinding.ActivityMainBinding
import com.weathermap.features.setting.SettingActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : BaseActivity<MainViewModel>() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        setDataListener()
        setEventListener()
    }

    private fun initView() {
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(this, R.drawable.row_divider)
        drawable?.let {
            itemDecorator.setDrawable(drawable)
        }
        binding.weatherList.addItemDecoration(itemDecorator)
    }

    private fun setDataListener() {
        viewModel.cityWeather.observe(this) {
            hideLoadingProgress()
            updateCityWeatherView(it)
        }
    }

    private fun updateCityWeatherView(cityWeatherModel: CityWeatherModel?) {
        if (cityWeatherModel?.list?.isNotEmpty() == true) {
            binding.tvEmpty.visibility = View.GONE
            binding.weatherList.visibility = View.VISIBLE
            val adapter = WeatherDayAdapter(cityWeatherModel.list)
            binding.weatherList.adapter = adapter
        } else {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.weatherList.visibility = View.GONE
        }
    }

    private fun setEventListener() {
        binding.btnGetWeather.setOnClickListener {
            doSearchTerm()
        }

        binding.txtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearchTerm()
                true
            } else false
        }

        binding.txtSearch.doAfterTextChanged { text ->
            setClearButtonVisibility(text)
        }

        binding.btnClear.setOnClickListener {
            binding.txtSearch.setText("")
        }

        binding.swipeContainer.setOnRefreshListener(OnRefreshListener { // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            doSearchTerm()
        })
    }

    private fun setClearButtonVisibility(text: Editable?) {
        if (TextUtils.isEmpty(text)) {
            binding.btnClear.visibility = View.INVISIBLE
        } else {
            binding.btnClear.visibility = View.VISIBLE
        }
    }

    private fun doSearchTerm() {
        if (binding.txtSearch.text.toString().length >= 3) {
            showLoadingProgress()
            viewModel.getCityWeather(binding.txtSearch.text.toString().trim())
            hideSoftKeyboard()
        } else {
            Toast.makeText(this, getString(R.string.term_search_required), Toast.LENGTH_SHORT)
                .show()
        }
        binding.swipeContainer.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSetting -> {
                openSetting()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openSetting() {
        val intent = Intent(this, SettingActivity::class.java)
        getResult.launch(intent)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }

    override val viewModel: MainViewModel
        get() = getViewModel()
}