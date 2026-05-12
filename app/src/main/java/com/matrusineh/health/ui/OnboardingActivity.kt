package com.matrusineh.health.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.matrusineh.health.R
import com.matrusineh.health.databinding.ActivityOnboardingBinding
import com.matrusineh.health.utils.PrefHelper

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    private val slides = listOf(
        Triple("🌸", "ಮಾತೃ-ಸ್ನೇಹಕ್ಕೆ ಸ್ವಾಗತ!\nWelcome to Matru-Sneh!",
            "ನಿಮ್ಮ ಗರ್ಭಾವಸ್ಥೆಯ ಆರೋಗ್ಯ ಮಾರ್ಗದರ್ಶಿ — ಇಂಟರ್ನೆಟ್ ಇಲ್ಲದೆ ಕೆಲಸ ಮಾಡುತ್ತದೆ!\nYour complete pregnancy guide — works fully OFFLINE!"),
        Triple("👶", "ಒದೆ ಎಣಿಕೆ & ತಪಾಸಣೆ\nKick Counter & Checkup",
            "ಮಗುವಿನ ಚಲನೆ ಟ್ರ್ಯಾಕ್ ಮಾಡಿ, ತಪಾಸಣೆ ದಿನಾಂಕ ನೆನಪಿಡಿ!\nTrack baby kicks and never miss a checkup!"),
        Triple("🥗", "ಪೋಷಣೆ & ಆರೋಗ್ಯ ಎಚ್ಚರಿಕೆ\nNutrition & Health Alerts",
            "ದಿನನಿತ್ಯದ ಆಹಾರ ಪಟ್ಟಿ & ತುರ್ತು ಎಚ್ಚರಿಕೆಗಳು!\nDaily food checklist & emergency danger sign alerts!")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PrefHelper.isOnboardingDone(this)) {
            goToNext()
            return
        }
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = OnboardingAdapter(slides)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButton(position)
            }
        })

        binding.btnNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < slides.size - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                finishOnboarding()
            }
        }

        binding.btnSkip.setOnClickListener { finishOnboarding() }
    }

    private fun updateButton(pos: Int) {
        if (pos == slides.size - 1) {
            binding.btnNext.text = getString(R.string.get_started)
        } else {
            binding.btnNext.text = getString(R.string.next)
        }
    }

    private fun finishOnboarding() {
        PrefHelper.setOnboardingDone(this)
        goToNext()
    }

    private fun goToNext() {
        if (PrefHelper.isProfileSet(this)) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, ProfileSetupActivity::class.java))
        }
        finish()
    }

    inner class OnboardingAdapter(private val data: List<Triple<String, String, String>>) :
        RecyclerView.Adapter<OnboardingAdapter.VH>() {

        inner class VH(view: View) : RecyclerView.ViewHolder(view) {
            val emoji: TextView = view.findViewById(R.id.tvEmoji)
            val title: TextView = view.findViewById(R.id.tvTitle)
            val desc: TextView = view.findViewById(R.id.tvDesc)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_onboarding_slide, parent, false)
            return VH(v)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.emoji.text = data[position].first
            holder.title.text = data[position].second
            holder.desc.text = data[position].third
        }

        override fun getItemCount() = data.size
    }
}
