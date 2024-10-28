package com.papb.lk2praktikum.data.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tugas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tugasDao(): TugasDao
}
