package com.jacob.datagov.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jacob.datagov.database.table.Record
import org.koin.dsl.module

@Database(entities = [Record::class],
    version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {

            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context, AppDatabase::class.java, "JACOB_DATABASE")
                            .allowMainThreadQueries()
                            .build() }
                }
            }

            return INSTANCE

        }

    }
}


