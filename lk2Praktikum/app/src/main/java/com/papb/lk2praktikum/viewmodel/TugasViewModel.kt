package com.papb.lk2praktikum.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.papb.lk2praktikum.data.model.local.Tugas
import com.papb.lk2praktikum.data.model.local.TugasDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TugasViewModel(application: Application) : AndroidViewModel(application) {

    private val database = Room.databaseBuilder(
        application,
        TugasDatabase::class.java,
        "tugas_database"
    ).build()

    private val tugasDao = database.tugasDao()
    private val _tugasList = MutableStateFlow<List<Tugas>>(emptyList())
    val tugasList: StateFlow<List<Tugas>> get() = _tugasList.asStateFlow()

    init {
        loadTugas()
    }

    private fun loadTugas() {
        viewModelScope.launch(Dispatchers.IO) {
            _tugasList.value = tugasDao.getAllTugas()
        }
    }

    fun addTugas(namaMatkul: String, detailTugas: String) {
        viewModelScope.launch(Dispatchers.IO) {
            tugasDao.insertTugas(Tugas(namaMatkul = namaMatkul, detailTugas = detailTugas))
            loadTugas()
        }
    }

    fun markTugasAsDone(tugas: Tugas) {
        viewModelScope.launch(Dispatchers.IO) {
            tugasDao.updateTugasStatus(tugas.id, true)
            loadTugas()
        }
    }

}
