package com.papb.lk2praktikum.data.model.local

import androidx.room.*

@Dao
interface TugasDao {
    @Query("SELECT * FROM tugas")
    fun getAllTugas(): List<Tugas>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTugas(tugas: Tugas)

    @Delete
    suspend fun deleteTugas(tugas: Tugas)

    @Query("UPDATE tugas SET selesai = :isSelesai WHERE id = :id")
    suspend fun updateTugasStatus(id: Int, isSelesai: Boolean)
}

