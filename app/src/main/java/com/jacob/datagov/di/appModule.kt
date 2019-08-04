package com.jacob.datagov.di

import com.jacob.datagov.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    //activity
    viewModel { MainViewModel() }


}

val appModule = listOf(viewModelModule)