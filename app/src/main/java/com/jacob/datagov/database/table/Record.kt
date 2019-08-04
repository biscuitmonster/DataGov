package com.jacob.datagov.database.table

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Record")
open class Record {

    @ColumnInfo(name = "volume_of_mobile_data")
    @SerializedName("volume_of_mobile_data")
    @Expose
    var volumeOfMobileData: String? = null
    @ColumnInfo(name = "quarter")
    @SerializedName("quarter")
    @Expose
    var quarter: String? = null
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("_id")
    @Expose
    var id: Int? = null

}