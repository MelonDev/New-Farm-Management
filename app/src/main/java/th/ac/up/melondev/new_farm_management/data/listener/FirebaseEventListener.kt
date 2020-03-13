package th.ac.up.melondev.new_farm_management.data.listener

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

interface FirebaseEventListener {

    fun onDataChanged(snapshot: DataSnapshot)
    fun onError(error :Int)
    fun onNotFound()

}