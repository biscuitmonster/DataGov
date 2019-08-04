package com.jacob.datagov.view

import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.jacob.datagov.R
import com.jacob.datagov.adapter.DataAdapter
import com.jacob.datagov.aop.SingleClick
import com.jacob.datagov.base.BaseActivity
import com.jacob.datagov.database.AppDatabase
import com.jacob.datagov.databinding.ActivityMainBinding
import com.jacob.datagov.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {


    private val mViewModel by viewModel<MainViewModel>()
    override fun getLayoutId(): Int = R.layout.activity_main

    var context : Context = this

    override fun initView() {

        mBinding.vm = mViewModel
        mViewModel.init(this)
        mViewModel.date.observe(this, Observer {
            //var yearString ="%"+ mViewModel.date.value+"%"
            generateAdapter("%$it%")
        })
    }

    @SingleClick
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLeft -> {
                mViewModel.calendarLeft()}
            R.id.btnRight ->{
                mViewModel.calendarRight()}
        }
    }
    fun generateAdapter(yearString:String){
        var listData = mViewModel.filterData(context,yearString)

        var adapter = DataAdapter(this, listData)
        mBinding.recyclerViewItemData.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerViewItemData.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}
