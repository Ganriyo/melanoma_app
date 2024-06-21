package com.dicoding.melanomaapp.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.melanomaapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrasiActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnMasuk: Button
    private lateinit var btnDaftar: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)
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
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.btn_login -> {
                val loginIntent = Intent(this@RegistrasiActivity, LoginActivity::class.java)
                startActivity(loginIntent)
            }
            R.id.btn_register -> signUp(etEmail.text.toString(), etPass.text.toString())
        }
    }

    private fun signUp(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    updateUI(user)
                    Toast.makeText(this@RegistrasiActivity, user.toString(), Toast.LENGTH_SHORT).show()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this@RegistrasiActivity, task.exception.toString(), Toast.LENGTH_SHORT).show()
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
            val intent = Intent(this@RegistrasiActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "RegistrasiActivity"
    }
}