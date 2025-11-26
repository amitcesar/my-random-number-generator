package com.github.amitcesar.mynumbergeneration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.github.amitcesar.mynumbergeneration.databinding.FragmentConfiguracaoDeSorteioBinding
import com.github.amitcesar.mynumbergeneration.databinding.FragmentResultadoSorteioBinding
import kotlinx.coroutines.launch
import kotlin.getValue
import kotlin.random.Random


class ResultadoSorteioFragment : Fragment() {

    private val viewModel: SorteioViewModel by activityViewModels()

    private var _binding: FragmentResultadoSorteioBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultadoSorteioBinding.inflate(inflater, container, false)
        val view = binding.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {


                lifecycleScope.launch {
                    viewModel.uiState.collect { uiState ->
                        tbDrawNumber.text =
                            getString(R.string.numero_do_sorteio, uiState.currentDrawNumber.toString())

                        clearLastDrewNumbers()
                        uiState.drawNumbers.forEach { drawNumber ->
                                gerarTextoDeNumeroSorteado(drawNumber)
                        }
                    }
                }



        }
    }

    private fun FragmentResultadoSorteioBinding.gerarTextoDeNumeroSorteado(drawNumber: Int) {
        val drawNumberTextView = TextView(requireContext()).apply {
            id = View.generateViewId()
            text = drawNumber.toString()
            setTextAppearance(R.style.TextAppearance_RobotoMono_Overline)
            textSize = 48f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.content_brand))
        }
        root.addView(drawNumberTextView)
        flowResultNumbersHelper.referencedIds =
            flowResultNumbersHelper.referencedIds.plus(drawNumberTextView.id)

    }

    private fun FragmentResultadoSorteioBinding.clearLastDrewNumbers(){
         flowResultNumbersHelper.referencedIds.forEach { componentID ->
             root.removeView(root.findViewById(componentID))
         }
    }


}