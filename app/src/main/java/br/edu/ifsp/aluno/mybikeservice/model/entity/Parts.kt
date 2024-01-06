package br.edu.ifsp.aluno.mybikeservice.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Parts (
    @PrimaryKey
    var numberSerie :String,
    var partDesc :String,
    var partModel : String,
    var manufacturer : String,
    var registerDate :String
) : Parcelable{
    companion object {
        const val MAINTENANCE_DONE_TRUE = 1
        const val MAINTENANCE_DONE_FALSE = 0
    }
}
