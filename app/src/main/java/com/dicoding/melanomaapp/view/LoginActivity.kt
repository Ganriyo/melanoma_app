package com.dicoding.melanomaapp.view

import com.dicoding.melanomaapp.R
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnMasuk: Button
    private lateinit var btnDaftar: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail = findViewById(R.id.email)
        etPass = findViewById(R.id.password)
        btnMasuk = findViewById(R.id.btn_login)
        btnDaftar = findViewById(R.id.btn_register)
        mAuth = FirebaseAuth.getInstance()
        btnMasuk.setOnClickListener(this)
        btnDaftar.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.btn_login -> login(etEmail.text.toString(), etPass.text.toString())
            R.id.btn_register -> {
                val registerIntent = Intent(this@LoginActivity, RegistrasiActivity::class.java)
                startActivity(registerIntent)
            }
        }
    }

    private fun login(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed - in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = mAuth.currentUser
                    Toast.makeText(this@LoginActivity, user.toString(), Toast.LENGTH_SHORT).show()
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this@LoginActivity, "Akun belum terdaftar atau inputan salah", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun validateForm(): Boolean {
        var result = true
        if (TextUtils.isEmpty(etEmail.text.toString())) {
            etEmail.error = "Required"
            result = false
        } else {
            etEmail.error = null
        }
        if (TextUtils.isEmpty(etPass.text.toString())) {
            etPass.error = "Required"
            result = false
        } else {
            etPass.error = null
        }
        return result
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
