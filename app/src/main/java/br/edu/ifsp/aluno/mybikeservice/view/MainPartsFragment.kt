package br.edu.ifsp.aluno.mybikeservice.view


import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.mybikeservice.controller.PartsController
import br.edu.ifsp.aluno.mybikeservice.databinding.FragmentMainPartsBinding
import br.edu.ifsp.aluno.mybikeservice.model.entity.Parts
import br.edu.ifsp.aluno.mybikeservice.view.adapter.OnPartsClickListener
import br.edu.ifsp.aluno.mybikeservice.view.adapter.PartAdapter


class MainPartsFragment : Fragment(), OnPartsClickListener {

    private lateinit var fmpb :FragmentMainPartsBinding

    private var partList :MutableList<Parts> = mutableListOf()

   private val partAdapter:PartAdapter by lazy {
       PartAdapter(partList,this)
   }

    private val navController: NavController by lazy {
        findNavController()
    }

    companion object{
        const val EXTRA_MAINTENANCE = "EXTRA_MAINTENANCE"
        const val MAINTENANCE_FRAGMENT_REQUEST_KEY = "MAINTENANCE_FRAGMENT_REQUEST_KEY"
    }

    private val partsController: PartsController by lazy {
        PartsController(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(MAINTENANCE_FRAGMENT_REQUEST_KEY){
                requestKey, bundle ->
            if (requestKey == MAINTENANCE_FRAGMENT_REQUEST_KEY){
                val parts = if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
                    bundle.getParcelable(EXTRA_MAINTENANCE,Parts::class.java)
                }else{
                    bundle.getParcelable(MainFragment.EXTRA_MAINTENANCE)
                }
                parts?.also { receivedParts ->
                    partList.indexOfFirst { it.numberSerie == receivedParts.numberSerie }.also { position ->
                        if (position!=-1){
                            partsController.editParts(receivedParts)
                            partList[position] = receivedParts
                            partAdapter.notifyItemChanged(position)
                        }else{
                            partsController.insertParts(receivedParts)
                            partList.add(receivedParts)
                            partAdapter.notifyItemInserted(partList.lastIndex)
                        }
                    }
                }
                (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    fmpb.root.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
        partsController.getParts()
    }


    override fun onPartClick(position: Int) = navigateToPartsFragment(position,false)

    override fun onRemovePartMenuItemClick(position: Int) {
        partsController.removeParts(partList[position])
        partList.removeAt(position)
        partAdapter.notifyItemRemoved(position)
    }


    override fun onEditPartMenuItemClick(position: Int) = navigateToPartsFragment(position,true)


    private fun navigateToPartsFragment(position: Int, editParts: Boolean) {
        partList[position].also {
            navController.navigate(
                MainFragmentDirections.actionMainFragmentToMainPartsFragment(it,editParts)
            )
        }
    }

    fun  updatePartsList(parts :List<Parts>){
        partList.clear()
        parts.forEachIndexed { index, part ->
            partList.add(part)
            partAdapter.notifyItemChanged(index)
        }
    }

}