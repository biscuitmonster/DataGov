package com.jacob.datagov.viewmodel

import com.jacob.datagov.database.table.Record
import android.databinding.ObservableField


class ItemDataViewModel{

    private var record:Record?=null
    var isDecrease = ObservableField<Boolean>()
    val quarter : String get() = record!!.quarter.toString()
    val volume : String get() =  record!!.volumeOfMobileData.toString()

    constructor(record: Record, isDecrease:Boolean){
        this.record = record
        this.isDecrease.set(isDecrease)

    }
}
