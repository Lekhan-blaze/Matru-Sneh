package com.matrusineh.health.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.matrusineh.health.R
import com.matrusineh.health.databinding.FragmentCheckupBinding
import com.matrusineh.health.viewmodel.CheckupViewModel
import com.matrusineh.health.viewmodel.CheckupViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class CheckupFragment : Fragment() {

    private var _binding: FragmentCheckupBinding? = null
    private val binding get() = _binding!!
    private val vm: CheckupViewModel by viewModels { CheckupViewModelFactory(requireActivity().application) }
    private val adapter = CheckupAdapter { vm.deleteCheckup(it) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCheckupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCheckups.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CheckupFragment.adapter
        }

        vm.upcomingCheckups.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            binding.tvNoCheckups.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        vm.nextCheckupDays.observe(viewLifecycleOwner) { days ->
            if (days == null) {
                binding.tvCountdownDays.text = "--"
                binding.cardCountdown.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.secondary_light))
            } else {
                binding.tvCountdownDays.text = days.toString()
                val color = when {
                    days <= 2 -> R.color.red_alert_light
                    days <= 7 -> R.color.warning_bg
                    else -> R.color.secondary_light
                }
                binding.cardCountdown.setCardBackgroundColor(ContextCompat.getColor(requireContext(), color))
                val textColor = when {
                    days <= 2 -> R.color.error
                    days <= 7 -> R.color.yellow_countdown
                    else -> R.color.green_countdown
                }
                binding.tvCountdownDays.setTextColor(ContextCompat.getColor(requireContext(), textColor))
            }
        }

        binding.btnAddCheckup.setOnClickListener { showAddCheckupDialog() }
    }

    private fun showAddCheckupDialog() {
        val types = arrayOf("Scan / ಸ್ಕ್ಯಾನ್", "TT Vaccine / ಟಿಟಿ ಲಸಿಕೆ",
            "Blood Test / ರಕ್ತ ಪರೀಕ್ಷೆ", "Doctor Visit / ವೈದ್ಯ ಭೇಟಿ",
            "Urine Test / ಮೂತ್ರ ಪರೀಕ್ಷೆ")
        var selectedType = types[0]
        var selectedDateMs = System.currentTimeMillis() + 7 * 24 * 3600 * 1000L

        val dialogView = LayoutInflater.from(requireContext())
            .inflate(android.R.layout.select_dialog_item, null)

        // Simple manual dialog
        val cal = Calendar.getInstance()
        val datePicker = DatePickerDialog(requireContext(), { _, y, m, d ->
            val selected = Calendar.getInstance().apply {
                set(y, m, d, 8, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
            selectedDateMs = selected.timeInMillis

            AlertDialog.Builder(requireContext())
                .setTitle("ತಪಾಸಣೆ ಪ್ರಕಾರ / Checkup Type")
                .setItems(types) { _, which ->
                    selectedType = types[which]
                    vm.addCheckup(selectedType, selectedDateMs)
                }
                .setNegativeButton(getString(R.string.cancel), null)
                .show()

        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        datePicker.datePicker.minDate = System.currentTimeMillis()
        datePicker.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
