package br.edu.ifsp.aluno.mybikeservice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.mybikeservice.R
import br.edu.ifsp.aluno.mybikeservice.databinding.FragmentPartBinding
import br.edu.ifsp.aluno.mybikeservice.model.entity.Parts


class PartFragment : Fragment() {
    private lateinit var fpb: FragmentPartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.new_part)

        fpb = FragmentPartBinding.inflate(inflater,container,false)


        fpb.run { savePartBt.setOnClickListener {
            setFragmentResult(MainPartsFragment.MAINTENANCE_FRAGMENT_REQUEST_KEY, Bundle().apply {
                putParcelable(
                    MainPartsFragment.EXTRA_MAINTENANCE, Parts(
                        numberSerieEt.text.toString(),
                        descPartEt.text.toString(),
                        modelPartsEt.text.toString(),
                        manufacturerPartEt.text.toString(),
                        dataRegisterPartEt.text.toString()
                    )
                )
            })
            findNavController().navigateUp()
        } }

       return  fpb.root

    }
}