package com.matrusineh.health.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.matrusineh.health.databinding.FragmentHealthAlertBinding
import com.matrusineh.health.viewmodel.HealthAlertViewModel
import com.matrusineh.health.viewmodel.HealthAlertViewModelFactory

class HealthAlertFragment : Fragment() {

    private var _binding: FragmentHealthAlertBinding? = null
    private val binding get() = _binding!!
    private val vm: HealthAlertViewModel by viewModels { HealthAlertViewModelFactory(requireActivity().application) }

    // Map index -> CheckBox
    private val dangerBoxes by lazy {
        mapOf(
            0 to binding.cbSwelling,
            1 to binding.cbHeadache,
            2 to binding.cbVision,
            3 to binding.cbAbdominal,
            4 to binding.cbBleeding,
            5 to binding.cbFever,
            6 to binding.cbNoMovement
        )
    }

    private var isUpdating = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHealthAlertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe selected signs
        vm.showRedAlert.observe(viewLifecycleOwner) { show ->
            binding.layoutRedAlert.visibility = if (show) View.VISIBLE else View.GONE
            binding.scrollNormal.visibility = if (show) View.GONE else View.VISIBLE
        }

        vm.selectedSigns.observe(viewLifecycleOwner) { selected ->
            isUpdating = true
            dangerBoxes.forEach { (index, cb) ->
                cb.isChecked = selected.contains(index)
            }
            isUpdating = false
        }

        // Checkbox listeners
        dangerBoxes.forEach { (index, cb) ->
            cb.setOnCheckedChangeListener { _, checked ->
                if (!isUpdating) vm.toggleSign(index, checked)
            }
        }

        // Call 104 button
        binding.btnCall104.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:104"))
            startActivity(intent)
        }

        // Dismiss / Clear all
        binding.btnDismissAlert.setOnClickListener {
            vm.clearAll()
            dangerBoxes.forEach { (_, cb) -> cb.isChecked = false }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
