package br.edu.ifsp.aluno.mybikeservice.controller

import androidx.room.Room
import br.edu.ifsp.aluno.mybikeservice.model.database.MaintenanceDatabase
import br.edu.ifsp.aluno.mybikeservice.model.database.MaintenanceDatabase.Companion.MAINTENANCE_LIST_DATABASE
import br.edu.ifsp.aluno.mybikeservice.model.entity.Maintenance
import br.edu.ifsp.aluno.mybikeservice.view.MainFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainController(private val mainFragment:MainFragment) {
    private val maintenanceDaoImpl = Room.databaseBuilder(
        mainFragment.requireContext(),
        MaintenanceDatabase::class.java,
        MAINTENANCE_LIST_DATABASE
    ).build().getMaintenanceDao()

    fun insertMaintenance(maintenance: Maintenance){
        CoroutineScope(Dispatchers.IO).launch {
            maintenanceDaoImpl.createMaintenance(maintenance)
        }
    }

    fun getMaintenance(){
        CoroutineScope(Dispatchers.IO).launch {
            val maintenance = maintenanceDaoImpl.retrieveMaintenance()
            mainFragment.updateMaintenanceList(maintenance)
        }
    }


    fun editMaintenance(maintenance: Maintenance){
        CoroutineScope(Dispatchers.IO).launch {
            maintenanceDaoImpl.updateMaintenance(maintenance)
        }
    }

    fun removeMaintenance(maintenance: Maintenance){
        CoroutineScope(Dispatchers.IO).launch {
            maintenanceDaoImpl.deleteMaintenance(maintenance)
        }
    }

}