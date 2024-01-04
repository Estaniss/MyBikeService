package br.edu.ifsp.aluno.mybikeservice.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Maintenance(
    @PrimaryKey
    var initialDate: String,
    var desc:String,
    var partList: String,
    var status: Int = MAINTENANCE_DONE_FALSE,
    var conclusionDate :String,
): Parcelable {
    companion object {
        const val MAINTENANCE_DONE_TRUE = 1
        const val MAINTENANCE_DONE_FALSE = 0
        const val STATUS_FINAL = "Finalizado"
        const val STATUS_WAIT = "Aguardando"
    }
}





