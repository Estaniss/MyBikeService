package br.edu.ifsp.aluno.mybikeservice.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.mybikeservice.R
import br.edu.ifsp.aluno.mybikeservice.databinding.TilePartsBinding
import br.edu.ifsp.aluno.mybikeservice.model.entity.Parts


class PartAdapter (
    private val partList: List<Parts>,
    private val onPartsClickListener: OnPartsClickListener
):RecyclerView.Adapter<PartAdapter.PartTileHolder>(){

    inner class  PartTileHolder(tilePartsBinding: TilePartsBinding):
            RecyclerView.ViewHolder(tilePartsBinding.root) {
        val numberSerieTv: TextView = tilePartsBinding.numberSerieTv
        val descPartTv: TextView = tilePartsBinding.descPartTv
        val modelPartsTv: TextView = tilePartsBinding.modelPartsTv
        val manufacturerPartTv: TextView = tilePartsBinding.manufacturerPartTv
        val dataRegisterPartTv: TextView = tilePartsBinding.dataRegisterPartTv

        init {
            tilePartsBinding.apply {
                root.run {
                    setOnCreateContextMenuListener { menu, _, _ ->
                        (onPartsClickListener as? Fragment)?.activity?.menuInflater?.inflate(
                          R.menu.context_menu_parts,menu
                        )
                        menu.findItem(R.id.removePartsMi)?.setOnMenuItemClickListener {
                            onPartsClickListener.onRemovePartMenuItemClick(adapterPosition)
                            true
                        }
                        menu.findItem(R.id.editPartsMi)?.setOnMenuItemClickListener {
                            onPartsClickListener.onEditPartMenuItemClick(adapterPosition)
                            true
                        }
                        setOnClickListener {
                            onPartsClickListener.onPartClick(adapterPosition)
                        }

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TilePartsBinding.inflate(
        LayoutInflater.from(parent.context),parent,false
    ).run { PartTileHolder(this) }

    override fun getItemCount() = partList.size

    override fun onBindViewHolder(holder: PartTileHolder, position: Int) {
        partList[position].let { parts ->
            with(holder){
                numberSerieTv.text = parts.numberSerie
                descPartTv.text = parts.partDesc
                modelPartsTv.text = parts.partModel
                manufacturerPartTv.text = parts.manufacturer
                dataRegisterPartTv.text = parts.registerDate

            }
        }

    }


}
