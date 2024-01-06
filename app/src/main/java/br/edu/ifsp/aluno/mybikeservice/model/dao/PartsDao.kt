package br.edu.ifsp.aluno.mybikeservice.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.aluno.mybikeservice.model.entity.Parts

@Dao
interface PartsDao {
    companion object {
        const val PARTS_LIST = "parts"
    }
    @Insert
    fun createMaintenance(parts: Parts)
    @Query("SELECT * FROM $PARTS_LIST")
    fun retrieveMaintenance(): List<Parts>
    @Update
    fun updateMaintenance(parts: Parts)
    @Delete
    fun deleteMaintenance(parts: Parts)
}