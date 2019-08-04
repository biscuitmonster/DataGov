package com.jacob.datagov.base


import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.Toast
import com.jacob.datagov.BR
import com.jacob.datagov.R

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(), Presenter {

    protected lateinit var mBinding: VB
    private lateinit var mContext: Context
    var doubleBackToExitPressedOnce = false
    private var dialogSpinner: Dialog? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())

        mBinding.setVariable(BR.presenter, this)
        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        mContext = this


        dialogSpinner = Dialog(this, android.R.style.Theme_Translucent)
        dialogSpinner!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogSpinner!!.setContentView(R.layout.dialog_custom_spinner)
        dialogSpinner!!.setCancelable(false)

        initView()
    }


    fun showDialogSpinner() {
        dialogSpinner!!.show()
    }

    fun dismissDialogSpinner() {
        if (dialogSpinner!!.isShowing)
            dialogSpinner!!.dismiss()
    }


    override fun onBackPressed() {

        if (!doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = false
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed( { doubleBackToExitPressedOnce = true }, 2000)
    }

    abstract fun initView()

    abstract fun getLayoutId(): Int

    override fun onClick(v: View?) {

    }



}