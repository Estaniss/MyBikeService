package br.edu.ifsp.aluno.mybikeservice.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.mybikeservice.R
import br.edu.ifsp.aluno.mybikeservice.databinding.TileMaintenanceBinding
import br.edu.ifsp.aluno.mybikeservice.model.entity.Maintenance
import br.edu.ifsp.aluno.mybikeservice.model.entity.Maintenance.Companion.MAINTENANCE_DONE_TRUE


class MaintenanceAdapter (
    private val maintenanceList : List<Maintenance>,
    private val  onMaintenanceClickListener : OnMaintenanceClickListener
) : RecyclerView.Adapter<MaintenanceAdapter.MaintenanceTileHolder>(){
    inner class MaintenanceTileHolder(tileMaintenanceBinding: TileMaintenanceBinding):
            RecyclerView.ViewHolder(tileMaintenanceBinding.root){
        val nameTv: TextView = tileMaintenanceBinding.nameTv
        val descTv: TextView = tileMaintenanceBinding.descTv
        val doneCb: CheckBox = tileMaintenanceBinding.doneCb
        val  statusTV : TextView = tileMaintenanceBinding.statusTv

        init {
            tileMaintenanceBinding.apply {
                root.run {
                    setOnCreateContextMenuListener { menu, _, _ ->
                        (onMaintenanceClickListener as? Fragment)?.activity?.menuInflater?.inflate(
                            R.menu.context_menu_maintenance,menu
                        )
                        menu.findItem(R.id.removeMaintenancekMi)?.setOnMenuItemClickListener {
                            onMaintenanceClickListener.onRemoveMaintenanceMenuItemClick(
                                adapterPosition
                            )
                            true
                        }
                        menu.findItem(R.id.editMaintenanceMi)?.setOnMenuItemClickListener {
                                onMaintenanceClickListener.onEditMaintenanceMenuItemClick(adapterPosition)
                                true
                        }
                       setOnClickListener {
                           onMaintenanceClickListener.onMaintenanceClick(adapterPosition)
                       }
                    }
                    doneCb.run {
                        setOnClickListener {
                            onMaintenanceClickListener.onDoneCheckBoxClick(adapterPosition,isChecked)
                        }
                    }
                }
            }
        }


            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TileMaintenanceBinding.inflate(
        LayoutInflater.from(parent.context),parent,false
    ).run { MaintenanceTileHolder(this) }

    override fun onBindViewHolder(holder: MaintenanceTileHolder, position: Int) {
        maintenanceList[position].let { maintenance ->
            with(holder){
                nameTv.text = maintenance.initialDate
                doneCb.isChecked= maintenance.status == MAINTENANCE_DONE_TRUE
                descTv.text= maintenance.desc
                statusTV.text = if (maintenance.status == MAINTENANCE_DONE_TRUE) Maintenance.STATUS_FINAL else Maintenance.STATUS_WAIT
            }
        }
    }

    override fun getItemCount() = maintenanceList.size
}