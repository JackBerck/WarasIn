package com.example.warasin

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager { // Hapus (context: Context) dari sini
    // Hapus inisialisasi sharedPreferences di sini

    // Konstanta tetap bisa di sini
    private const val PREF_NAME = "warasin_pref"
    private const val KEY_ONBOARDING_FINISHED = "onboarding_finished"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_USER_EMAIL = "user_email"
    private const val KEY_USER_PASSWORD = "user_password"

    // Fungsi untuk mendapatkan SharedPreferences, bisa dibuat private helper
    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setOnboardingFinished(context: Context, finished: Boolean) {
        // Dapatkan SharedPreferences di dalam fungsi
        val sharedPreferences = getPreferences(context)
        sharedPreferences.edit().putBoolean(KEY_ONBOARDING_FINISHED, finished).apply()
    }

    fun isOnboardingFinished(context: Context): Boolean {
        // Dapatkan SharedPreferences di dalam fungsi
        val sharedPreferences = getPreferences(context)
        return sharedPreferences.getBoolean(KEY_ONBOARDING_FINISHED, false)
    }

    fun saveUser(context: Context, name: String, email: String, password: String) { // Tambahkan context
        val sharedPreferences = getPreferences(context)
        sharedPreferences.edit()
            .putString(KEY_USER_NAME, name)
            .putString(KEY_USER_EMAIL, email)
            .putString(KEY_USER_PASSWORD, password)
            .apply()
    }

    fun checkLogin(context: Context, email: String, password: String): Boolean { // Tambahkan context
        val sharedPreferences = getPreferences(context)
        val savedEmail = sharedPreferences.getString(KEY_USER_EMAIL, null)
        val savedPassword = sharedPreferences.getString(KEY_USER_PASSWORD, null)

        return savedEmail == email && savedPassword == password
    }

    fun isLoggedIn(context: Context): Boolean { // Tambahkan context
        val sharedPreferences = getPreferences(context)
        return sharedPreferences.contains(KEY_USER_EMAIL)
    }

    fun logout(context: Context) { // Tambahkan context
        val sharedPreferences = getPreferences(context)
        sharedPreferences.edit()
            .remove(KEY_USER_NAME)
            .remove(KEY_USER_EMAIL)
            .remove(KEY_USER_PASSWORD)
            .apply()
    }
}
