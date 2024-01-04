package br.edu.ifsp.aluno.mybikeservice.view

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.aluno.mybikeservice.R
import br.edu.ifsp.aluno.mybikeservice.databinding.FragmentMainBinding
import br.edu.ifsp.aluno.mybikeservice.model.entity.Maintenance
import br.edu.ifsp.aluno.mybikeservice.view.adapter.MaintenanceAdapter
import br.edu.ifsp.aluno.mybikeservice.view.adapter.OnMaintenanceClickListener


class MainFragment : Fragment() , OnMaintenanceClickListener{

    private lateinit var fmb: FragmentMainBinding

    private val maintenanceList: MutableList<Maintenance> = mutableListOf()

    private val maintenanceAdapter:MaintenanceAdapter by lazy {
        MaintenanceAdapter(maintenanceList,this)
    }

    private val navController: NavController by lazy {
        findNavController()
    }


    companion object{
        const val EXTRA_MAINTENANCE = "EXTRA_MAINTENANCE"
        const val MAINTENANCE_FRAGMENT_REQUEST_KEY = "MAINTENANCE_FRAGMENT_REQUEST_KEY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(MAINTENANCE_FRAGMENT_REQUEST_KEY){
            requestKey, bundle ->
            if (requestKey == MAINTENANCE_FRAGMENT_REQUEST_KEY){
                val maintenance = if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
                    bundle.getParcelable(EXTRA_MAINTENANCE,Maintenance::class.java)
                }else{
                    bundle.getParcelable(EXTRA_MAINTENANCE)
                }
                maintenance?.also { receivedMaintenance ->
                    maintenanceList.indexOfFirst { it.initialDate == receivedMaintenance.initialDate }.also { position ->
                        if (position!=-1){
                            maintenanceList[position] = receivedMaintenance
                            maintenanceAdapter.notifyItemChanged(position)
                        }else{
                            maintenanceList.add(receivedMaintenance)
                            maintenanceAdapter.notifyItemInserted(maintenanceList.lastIndex)
                        }
                    }
                }
                (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    fmb.root.windowToken,
                    HIDE_NOT_ALWAYS
                )


            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle=getString(R.string.maintenance_list)
        fmb = FragmentMainBinding.inflate(inflater,container,false).apply {
            maintenanceRv.layoutManager = LinearLayoutManager(context)
            maintenanceRv.adapter = maintenanceAdapter

            addFab.setOnClickListener {
                navController.navigate(
                    MainFragmentDirections.actionMainFragmentToMaintenanceFragment(null,editMaintenance = false)
                )
            }

        }
    return fmb.root

    }



    override fun onMaintenanceClick(position: Int) = navigateToMaintenanceFragment(position,false)

    override fun onRemoveMaintenanceMenuItemClick(position: Int) {
        maintenanceList.removeAt(position)
        maintenanceAdapter.notifyItemRemoved(position)
    }

    override fun onEditMaintenanceMenuItemClick(position: Int) = navigateToMaintenanceFragment(position,true)

    override fun onDoneCheckBoxClick(position: Int, checked: Boolean) {
      maintenanceList[position].apply {
          status = if (checked) Maintenance.MAINTENANCE_DONE_TRUE else Maintenance.MAINTENANCE_DONE_FALSE
      }
    }



    private fun navigateToMaintenanceFragment(position: Int, editTask: Boolean) {
        maintenanceList[position].also {
            navController.navigate(
                MainFragmentDirections.actionMainFragmentToMaintenanceFragment(it, editTask)
            )
        }
    }
}