package br.edu.ifsp.aluno.mybikeservice.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Maintenance(
    @PrimaryKey
    var desc:String = "",
    var initialDate: Long = INVALID_TIME,
    var partList: String = "" ,
    var status: Int = MAINTENANCE_DONE_FALSE,
    var conclusionDate :Long = INVALID_TIME,
): Parcelable {
    companion object {
        const val INVALID_TIME = -1L
        const val MAINTENANCE_DONE_TRUE = 1
        const val MAINTENANCE_DONE_FALSE = 0
    }
}





