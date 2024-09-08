package com.example.myappl5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)


        val list : MutableList<ListItem> = mutableListOf()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewId)

        recyclerView.adapter = listAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val firebaseDatabase = FirebaseDatabase.getInstance()

        firebaseDatabase.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for(child in snapshot.children){
                    list.add(ListItem(child.key.toString(), child.value.toString()))
                }
                (recyclerView.adapter as listAdapter).notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(
                    "RECYCLERACTerr", "onCancelled: err"+error.message)
            }


        })


    }
}