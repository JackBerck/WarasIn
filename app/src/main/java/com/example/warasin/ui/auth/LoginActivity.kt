package com.example.warasin.ui.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.warasin.MainActivity
import com.example.warasin.PreferenceManager
import com.example.warasin.R
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<TextInputEditText>(R.id.et_email)
        val etPassword = findViewById<TextInputEditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        // val btnGoogle = findViewById<Button>(R.id.btn_google)
        val tvRegister = findViewById<TextView>(R.id.tv_register)

        val fullText = "Belum punya akun? Daftar"
        val spannable = SpannableString(fullText)

        val startIndex = fullText.indexOf("Daftar")
        val endIndex = startIndex + "Daftar".length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(this@LoginActivity, R.color.blue_600) // pastikan warna biru ada di colors.xml
                ds.isUnderlineText = false
            }
        }

        spannable.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvRegister.text = spannable
        tvRegister.movementMethod = LinkMovementMethod.getInstance()
        tvRegister.highlightColor = Color.TRANSPARENT // biar gak ada highlight saat diklik

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInput(email, password)) {
                // Cek login dengan SharedPreferences (hanya contoh)
                if (PreferenceManager.checkLogin(this, email, password)) {
                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Email atau kata sandi salah", Toast.LENGTH_SHORT).show()
                }
            }
        }

        /*btnGoogle.setOnClickListener {
            // Untuk saat ini, kita hanya menampilkan toast
            Toast.makeText(this, "Login dengan Google", Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Kata sandi tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}