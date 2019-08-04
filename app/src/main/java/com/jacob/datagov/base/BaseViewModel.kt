package com.jacob.datagov.base


import android.arch.lifecycle.ViewModel
import android.content.Context


open class BaseViewModel : ViewModel(){

    lateinit var context: Context

}