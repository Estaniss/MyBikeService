package br.edu.ifsp.aluno.mybikeservice.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.aluno.mybikeservice.model.entity.Maintenance

@Dao
interface MaintenanceDao {
    companion object {
        const val MAINTENANCE_LIST = "maintenance"
    }
    @Insert
    fun createMaintenance(maintenance: Maintenance)
    @Query("SELECT * FROM $MAINTENANCE_LIST")
    fun retrieveMaintenance(): List<Maintenance>
    @Update
    fun updateMaintenance(maintenance: Maintenance)
    @Delete
    fun deleteMaintenance(maintenance: Maintenance)
}