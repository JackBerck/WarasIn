package com.example.warasin.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.warasin.PreferenceManager
import com.example.warasin.R
import com.example.warasin.ui.auth.RegisterActivity
import com.example.warasin.ui.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = if (PreferenceManager.isOnboardingFinished(this)) {
            Intent(this, RegisterActivity::class.java)
        } else {
            Intent(this, OnboardingActivity::class.java)
        }

        setContentView(R.layout.activity_splash)

        startActivity(intent)
        finish()
    }
}