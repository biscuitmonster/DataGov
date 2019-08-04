package com.jacob.datagov.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.jacob.datagov.database.table.Record

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(listRecord :List<Record>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(record :Record)

    @Query("SELECT * FROM Record WHERE id =:id limit 1")
    fun selectData(id:Int):Record

    @Query("SELECT * FROM Record WHERE quarter like :year ORDER BY quarter ASC")
    fun selectData(year:String):List<Record>

    @Query("SELECT * FROM Record")
    fun selectAllData():List<Record>


}