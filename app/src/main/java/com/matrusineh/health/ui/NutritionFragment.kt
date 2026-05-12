package com.matrusineh.health.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.matrusineh.health.R
import com.matrusineh.health.databinding.FragmentNutritionBinding
import com.matrusineh.health.viewmodel.NutritionViewModel
import com.matrusineh.health.viewmodel.NutritionViewModelFactory

class NutritionFragment : Fragment() {

    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!
    private val vm: NutritionViewModel by viewModels { NutritionViewModelFactory(requireActivity().application) }

    // Map index -> CheckBox
    private val checkboxes by lazy {
        mapOf(
            0 to binding.cbRagi,
            1 to binding.cbGreens,
            2 to binding.cbPulses,
            3 to binding.cbMilk,
            4 to binding.cbEgg,
            5 to binding.cbBanana,
            6 to binding.cbWater,
            7 to binding.cbIron
        )
    }

    private var isUpdating = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe today's logs from DB
        vm.todayLogs.observe(viewLifecycleOwner) { logs ->
            isUpdating = true
            val checkedMap = logs.associateBy { it.itemIndex }
            checkboxes.forEach { (index, cb) ->
                cb.isChecked = checkedMap[index]?.checked ?: false
            }
            isUpdating = false
            vm.updateCheckedCount(logs)
        }

        vm.checkedCount.observe(viewLifecycleOwner) { count ->
            binding.progressNutrition.progress = count
            binding.tvNutritionProgress.text = getString(R.string.nutrition_progress, count)
            binding.tvCompletionMsg.visibility = if (count >= 8) View.VISIBLE else View.GONE
        }

        // Set listeners for each checkbox
        checkboxes.forEach { (index, cb) ->
            cb.setOnCheckedChangeListener { _, checked ->
                if (!isUpdating) {
                    vm.setItemChecked(index, checked)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
