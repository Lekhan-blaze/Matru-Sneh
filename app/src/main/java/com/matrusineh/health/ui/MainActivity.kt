package com.matrusineh.health.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.matrusineh.health.R
import com.matrusineh.health.databinding.ActivityMainBinding
import com.matrusineh.health.data.model.MilestoneData
import com.matrusineh.health.viewmodel.ProfileViewModel
import com.matrusineh.health.viewmodel.ProfileViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val profileVm: ProfileViewModel by viewModels { ProfileViewModelFactory(application) }

    private val notifPermLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* granted or denied — continue either way */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Request notification permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                notifPermLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // Set up Navigation
        val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNav.setupWithNavController(navController)

        // Observe profile for header
        profileVm.profile.observe(this) { profile ->
            if (profile != null) {
                binding.tvMotherName.text = profile.name
                val week = profileVm.getCurrentWeek(profile)
                binding.tvWeekInfo.text = "ವಾರ / Week $week"
                val milestone = MilestoneData.getMilestone(week)
                if (milestone != null) {
                    binding.cardMilestone.visibility = android.view.View.VISIBLE
                    binding.tvMilestoneDesc.text = milestone.descriptionKn + "\n" + milestone.descriptionEn
                } else {
                    binding.cardMilestone.visibility = android.view.View.GONE
                }
            } else {
                binding.tvMotherName.text = ""
                binding.tvWeekInfo.text = ""
                binding.cardMilestone.visibility = android.view.View.GONE
            }
        }
    }
}
