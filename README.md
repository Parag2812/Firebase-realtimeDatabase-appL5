REDME.MD
L5
REAL TIME DATABASE FIREBASE
REALTIME CHANGE ON RecyclerView

     firebaseDatabase.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for(child in snapshot.children){
                    list.add(ListItem(child.key.toString(), child.value.toString()))
                }
                (recyclerView.adapter as listAdapter).notifyDataSetChanged()

            }
