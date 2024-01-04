package br.edu.ifsp.aluno.mybikeservice.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.edu.ifsp.aluno.mybikeservice.R
import br.edu.ifsp.aluno.mybikeservice.databinding.FragmentMaintenanceBinding
import br.edu.ifsp.aluno.mybikeservice.model.entity.Maintenance
import br.edu.ifsp.aluno.mybikeservice.model.entity.Maintenance.Companion.MAINTENANCE_DONE_TRUE
import br.edu.ifsp.aluno.mybikeservice.view.MainFragment.Companion.EXTRA_MAINTENANCE
import br.edu.ifsp.aluno.mybikeservice.view.MainFragment.Companion.MAINTENANCE_FRAGMENT_REQUEST_KEY


class MaintenanceFragment : Fragment() {
    private  lateinit var fmb : FragmentMaintenanceBinding
    private val navigationArgs:MaintenanceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.details)

        fmb = FragmentMaintenanceBinding.inflate(inflater,container,false)

        val receivedMaintenance = navigationArgs.maintenance
        receivedMaintenance?.also {maintenance ->  
            with(fmb){
                nameEt.setText(maintenance.initialDate.toString())
                stausCb.isChecked = maintenance.status == MAINTENANCE_DONE_TRUE
                navigationArgs.editMaintenance.also { editMaintenance ->
                    nameEt.isEnabled = editMaintenance
                    stausCb.isEnabled = editMaintenance
                    saveBt.visibility = if (editMaintenance) VISIBLE else GONE
                }
            }
        }

        fmb.run { saveBt.setOnClickListener {
            setFragmentResult(MAINTENANCE_FRAGMENT_REQUEST_KEY, Bundle().apply {
                putParcelable(
                    EXTRA_MAINTENANCE, Maintenance(
                        nameEt.text.toString(),
                    )
                )
            })
            findNavController().navigateUp()
        } }
        return fmb.root
    }

}