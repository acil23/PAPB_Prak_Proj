package com.papb.lk2praktikum.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tugas")
data class Tugas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaMatkul: String,
    val detailTugas: String,
    val selesai: Boolean = false
)

