package com.example.myappl5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myappl5.R.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val wellcomeText = findViewById<TextView>(id.wellcome)
        val logoutBtn = findViewById<TextView>(id.logoutBtn)
        val auth = FirebaseAuth.getInstance()

        val userEmail = auth.currentUser?.email
        wellcomeText.append(userEmail)

        val key = findViewById<EditText>(R.id.editTextKey)
        val value = findViewById<EditText>(R.id.editTextValue)
        val saveButton = findViewById<Button>(R.id.saveDataBtn)
        val viewDataButton = findViewById<Button>(R.id.viewDataBtn)

        val database = FirebaseDatabase.getInstance()


        saveButton.setOnClickListener {

            database.reference.child(key.text.toString()).setValue(value.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "data saved"+(it.exception?.message), Toast.LENGTH_SHORT).show()

                    }
                }
        }

        viewDataButton.setOnClickListener {
            intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }


        logoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}