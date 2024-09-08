package com.example.myappl5

import android.annotation.SuppressLint
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        val email = findViewById<TextView>(R.id.editTextEmailAddress)
        val password = findViewById<TextView>(R.id.editTextPassword)
        val confirmPassword = findViewById<TextView>(R.id.editTextConfirmPassword)
        val signup = findViewById<Button>(R.id.signUpbtn)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val loginIntent = findViewById<TextView>(R.id.loginIntent)
        val auth = FirebaseAuth.getInstance()

        loginIntent.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
               startActivity(intent)
        }


        signup.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val cmfPasswordText = confirmPassword.text.toString()

            if (TextUtils.isEmpty(emailText)|| TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(cmfPasswordText)){
                Toast.makeText(this , "Credentials Can't be Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passwordText.length < 6 ){
                Toast.makeText(this , "Password Length should be more then 6 char ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (! TextUtils.equals(passwordText, cmfPasswordText)){
                Toast.makeText(this , "Both password should match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE


            auth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()


                    } else {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, " "+ (task.exception?.message), Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }
}