package com.matrusineh.health.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.matrusineh.health.R
import com.matrusineh.health.databinding.FragmentKickCounterBinding
import com.matrusineh.health.viewmodel.KickViewModel
import com.matrusineh.health.viewmodel.KickViewModelFactory
import kotlinx.coroutines.launch
import java.util.Calendar

class KickCounterFragment : Fragment() {

    private var _binding: FragmentKickCounterBinding? = null
    private val binding get() = _binding!!
    private val vm: KickViewModel by viewModels { KickViewModelFactory(requireActivity().application) }
    private val summaryAdapter = WeeklyKickAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentKickCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvWeeklySummary.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = summaryAdapter
        }

        // Kick Button
        binding.btnKick.setOnClickListener {
            val recorded = vm.logKick()
            if (!recorded) {
                Toast.makeText(requireContext(),
                    "ತುಂಬಾ ವೇಗ! 2 ಸೆಕೆಂಡ್ ತಡೆಯಿರಿ / Too fast! Wait 2 seconds",
                    Toast.LENGTH_SHORT).show()
            } else {
                binding.btnKick.animate().scaleX(0.88f).scaleY(0.88f).setDuration(80)
                    .withEndAction {
                        binding.btnKick.animate().scaleX(1f).scaleY(1f).setDuration(80).start()
                    }.start()
            }
        }

        // Observers
        vm.todayCount.observe(viewLifecycleOwner) { binding.tvTodayKicks.text = it.toString() }
        vm.hourCount.observe(viewLifecycleOwner) { binding.tvHourKicks.text = it.toString() }
        vm.last2HrCount.observe(viewLifecycleOwner) { binding.tvLast2HrKicks.text = it.toString() }
        vm.showLowAlert.observe(viewLifecycleOwner) { show ->
            binding.cardLowMovement.visibility = if (show) View.VISIBLE else View.GONE
        }
        vm.kicksToday.observe(viewLifecycleOwner) {
            vm.refreshCounts()
            loadWeeklySummary()
        }

        // Reset
        binding.btnResetKicks.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.reset_confirm_title))
                .setMessage(getString(R.string.reset_confirm_msg))
                .setPositiveButton(getString(R.string.yes_reset)) { _, _ ->
                    vm.resetTodayKicks()
                }
                .setNegativeButton(getString(R.string.cancel), null)
                .show()
        }
    }

    private fun loadWeeklySummary() {
        lifecycleScope.launch {
            val summary = vm.getWeeklySummary()
            summaryAdapter.submitList(summary)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
