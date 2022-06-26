package com.weathermap.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.weathermap.R
import com.weathermap.utils.PrefKeys
import com.weathermap.utils.SharedPrefUtils

abstract class BaseActivity<viewModel : BaseViewModel> : AppCompatActivity() {
    protected abstract val viewModel: BaseViewModel
    var loadingDialog: android.app.AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(SharedPrefUtils.getIntData(this, PrefKeys.KEY_THEME_ID));
        initBaseDataListener()
    }

    private fun initBaseDataListener() {
        viewModel.handleErrorMessage.observe(this){
            it?.let { message->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showLoadingProgress() {
        if (loadingDialog == null) {
            val builder = android.app.AlertDialog.Builder(this, R.style.LoadingDialog)
            builder.setCancelable(true)
            builder.setView(R.layout.view_loading_dialog)
            loadingDialog = builder.create()
            loadingDialog?.show()
        }
    }

    fun hideLoadingProgress() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                currentFocus?.windowToken, 0
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoadingProgress()
    }
}