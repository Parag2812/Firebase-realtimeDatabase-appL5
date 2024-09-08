package com.example.myappl5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val email = findViewById<TextView>(R.id.editTextLoginEmailAddress)
        val password = findViewById<TextView>(R.id.editTextLoginPassword)
        val loginbtn = findViewById<Button>(R.id.loginbtn)
        val signupIntent = findViewById<TextView>(R.id.signupIntent)


        val auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        loginbtn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()


            if ( TextUtils.isEmpty(password) || TextUtils.isEmpty(email)){
                Toast.makeText(this , "Credentials Can't be Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, " "+ (task.exception?.message), Toast.LENGTH_SHORT).show()
                    }
                }


        }



        signupIntent.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}