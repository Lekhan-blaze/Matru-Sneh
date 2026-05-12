package com.matrusineh.health.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.matrusineh.health.databinding.ActivityProfileSetupBinding
import com.matrusineh.health.utils.PrefHelper
import com.matrusineh.health.viewmodel.ProfileViewModel
import com.matrusineh.health.viewmodel.ProfileViewModelFactory

class ProfileSetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileSetupBinding
    private val vm: ProfileViewModel by viewModels { ProfileViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text?.toString()?.trim() ?: ""
            val weekStr = binding.etWeek.text?.toString()?.trim() ?: ""
            val week = weekStr.toIntOrNull()

            if (name.isEmpty()) {
                binding.tilName.error = "ಹೆಸರು ನಮೂದಿಸಿ / Please enter your name"
                return@setOnClickListener
            }
            if (week == null || week < 4 || week > 42) {
                binding.tilWeek.error = "4–42 ಒಳಗೆ ವಾರ ನಮೂದಿಸಿ / Enter week between 4–42"
                return@setOnClickListener
            }

            vm.saveProfile(name, week)
            PrefHelper.setProfileSet(this)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
