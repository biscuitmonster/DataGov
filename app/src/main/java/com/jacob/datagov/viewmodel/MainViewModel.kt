package com.jacob.datagov.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.widget.Toast
import com.jacob.datagov.api.ApiClient
import com.jacob.datagov.api.response.ResponseGetAction
import com.jacob.datagov.base.BaseViewModel
import com.jacob.datagov.database.AppDatabase
import com.jacob.datagov.database.table.Record
import com.jacob.datagov.entity.DataResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class MainViewModel: BaseViewModel(){

    var years = listOf(2015,2016,2017,2018)
    var yearCount = 0

    val date = MutableLiveData<String>()

    val dataResult = MutableLiveData<DataResult>()

    fun init(context: Context){
        this.context = context

        ApiClient.create(context).getActionData()
            .enqueue(object : Callback<ResponseGetAction> {
                override fun onResponse(
                    call: Call<ResponseGetAction>,
                    response: Response<ResponseGetAction>
                ) {
                    AppDatabase.getDatabase(context)!!.dataDao().insertAllData(response.body()!!.result!!.records!!)
                    var result = DataResult()
                    result.isBoolean = true
                    dataResult.value = result
                    setDate(years[yearCount].toString())
                }

                override fun onFailure(call: Call<ResponseGetAction>, t: Throwable) {
                    var result = DataResult()
                    result.isBoolean = false
                    result.msg = t.toString()
                    dataResult.value = result
                    setDate(years[yearCount].toString())
                }
            })

    }

    fun setDate(yearsText :String){
        date.value = yearsText
    }

    fun filterData(context:Context , yearString:String):List<Record>{
        return AppDatabase.getDatabase(context)!!.dataDao().selectData(yearString)

    }


    fun calendarLeft(){
        if(yearCount >0){
            setDate(years[--yearCount].toString())
        }else{
            Toast.makeText(context,"Out of range",Toast.LENGTH_LONG).show()
        }
    }

    fun calendarRight(){
        if(yearCount <years.size-1){
            setDate(years[++yearCount].toString())
        }else{
            Toast.makeText(context,"Out of range",Toast.LENGTH_LONG).show()
        }
    }

}