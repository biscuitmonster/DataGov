package com.jacob.datagov.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.jacob.datagov.di.appModule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.MockitoAnnotations

class MainViewModelTest: KoinTest{

    val viewModel : MainViewModel by inject()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        startKoin{modules(appModule)}
    }


    @Test
    fun `Test date in MutableLiveData is working`(){
        viewModel.setDate("2015")
        assertThat(viewModel.date.value!!.toString(),`is`("2015"))

    }



}


