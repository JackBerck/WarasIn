package com.example.warasin.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.warasin.MainActivity
import com.example.warasin.PreferenceManager
import com.example.warasin.R
import com.example.warasin.model.OnboardingItem
import com.example.warasin.ui.auth.RegisterActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var nextButton: Button
    private lateinit var skipButton: Button
    private lateinit var adapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        nextButton = findViewById(R.id.btn_next)
        skipButton = findViewById(R.id.btn_skip)

        val onboardingItems = listOf(
            OnboardingItem(
                R.drawable.health_flag, // dari Figma kamu
                "Jangan Lagi Lupa Minum Obat",
                "Aplikasi akan mengingatkan jadwal obatmu secara otomatis, agar pengobatan lebih teratur dan efektif."
            ),
            OnboardingItem(
                R.drawable.health_book,
                "Pantau Kesehatan dengan Mudah",
                "Catat tekanan darah, gula darah, atau kondisi harianmu hanya dengan beberapa klik."
            ),
            OnboardingItem(
                R.drawable.grandparent,
                "Mudah Digunakan untuk Semua",
                "Desain sederhana dengan tulisan besar dan navigasi mudah, cocok untuk lansia maupun pasien penyakit kronis."
            )
        )

        val skipButton = findViewById<Button>(R.id.btn_skip)

        skipButton.setOnClickListener {
            PreferenceManager.setOnboardingFinished(this, true)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == onboardingItems.lastIndex) {
                    nextButton.text = "Mulai Sekarang"
                    skipButton.visibility = View.GONE

                    val layoutParams = nextButton.layoutParams
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                    nextButton.layoutParams = layoutParams
                } else {
                    nextButton.text = "Lanjut ➝"
                    skipButton.visibility = View.VISIBLE

                    val layoutParams = nextButton.layoutParams
                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                    nextButton.layoutParams = layoutParams
                }
            }
        })

        adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_indicator)
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        nextButton.setOnClickListener {
            if (viewPager.currentItem < onboardingItems.size - 1) {
                viewPager.currentItem += 1
            } else {
                // Selesai onboarding, lanjut ke MainActivity
                PreferenceManager.setOnboardingFinished(this, true)
                startActivity(Intent(this, RegisterActivity::class.java))

                finish()
            }
        }
    }
}