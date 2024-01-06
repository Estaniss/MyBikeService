package br.edu.ifsp.aluno.mybikeservice.controller

import androidx.room.Room
import br.edu.ifsp.aluno.mybikeservice.model.database.MaintenanceDatabase
import br.edu.ifsp.aluno.mybikeservice.model.entity.Parts
import br.edu.ifsp.aluno.mybikeservice.view.MainPartsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartsController (private val mainPartsFragment: MainPartsFragment) {
    private val partsdaoImpl = Room.databaseBuilder(
        mainPartsFragment.requireContext(),
        MaintenanceDatabase::class.java,
        MaintenanceDatabase.MAINTENANCE_LIST_DATABASE
    ).build().getPartsDao()

    fun insertParts(parts: Parts){
        CoroutineScope(Dispatchers.IO).launch {
            partsdaoImpl.createMaintenance(parts)
        }
    }

    fun getParts(){
        CoroutineScope(Dispatchers.IO).launch {
            val parts = partsdaoImpl.retrieveMaintenance()
            mainPartsFragment.updatePartsList(parts)
        }
    }


    fun editParts(parts: Parts){
        CoroutineScope(Dispatchers.IO).launch {
            partsdaoImpl.updateMaintenance(parts)
        }
    }

    fun removeParts(parts: Parts){
        CoroutineScope(Dispatchers.IO).launch {
            partsdaoImpl.deleteMaintenance(parts)
        }
    }

}