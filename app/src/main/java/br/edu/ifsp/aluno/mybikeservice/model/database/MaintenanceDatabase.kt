package br.edu.ifsp.aluno.mybikeservice.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifsp.aluno.mybikeservice.model.dao.MaintenanceDao
import br.edu.ifsp.aluno.mybikeservice.model.dao.PartsDao
import br.edu.ifsp.aluno.mybikeservice.model.entity.Maintenance
import br.edu.ifsp.aluno.mybikeservice.model.entity.Parts


@Database(entities = [Maintenance::class, Parts::class], version = 2)
abstract class MaintenanceDatabase:RoomDatabase() {
    companion object {
        const val MAINTENANCE_LIST_DATABASE = "MaintenanceDatabase"
    }
    abstract fun getMaintenanceDao(): MaintenanceDao
    abstract fun getPartsDao():PartsDao
}