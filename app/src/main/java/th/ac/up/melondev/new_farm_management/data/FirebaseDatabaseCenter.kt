package th.ac.up.melondev.new_farm_management.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import th.ac.up.melondev.new_farm_management.data.listener.FirebaseEventListener

class FirebaseDatabaseCenter {

    companion object {
        fun createService(): FirebaseDatabaseCenter =
            FirebaseDatabaseCenter().createService()
    }


    private var database: DatabaseReference? = null
    private var authenticator: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    fun createService(): FirebaseDatabaseCenter {
        this.authenticator = FirebaseAuth.getInstance()
        checkCurrentUser()
        return this
    }

    private fun checkCurrentUser() {
        authenticator?.currentUser?.let {
            user = it
            accessDatabase()
        } ?: run {
            user = null
            database = null
        }
    }

    private fun accessDatabase() {
        user?.let {
            Log.e("USER", it.uid.toString())
            database = Firebase.database.reference.child("ผู้ใช้").child(it.uid)
        }
    }

    private fun refreshAccess(): Boolean {
        checkCurrentUser()
        authenticator?.currentUser?.let {
            return true
        } ?: run {
            return false
        }
    }

    fun isAccessDatabase(): Boolean {
        refreshAccess()
        database?.let {
            return true
        } ?: run {
            return false
        }
    }

    fun loadEvent(path: String? = null, listener: FirebaseEventListener) {

        refreshAccess()

        database?.let { ref ->
            path?.let {
                startEvent(ref.child(it), listener)
            } ?: run {
                startEvent(ref, listener)
            }
        } ?: run {

            listener.onError(DatabaseError.INVALID_TOKEN)
        }
    }

    private fun startEvent(reference: DatabaseReference?, listener: FirebaseEventListener) {
        reference?.let {
            it.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    listener.onError(p0.code)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        listener.onDataChanged(p0)
                    } else {
                        listener.onNotFound()
                    }
                }
            })
        } ?: run {

            listener.onError(DatabaseError.INVALID_TOKEN)
        }
    }

    fun signinByEmail(email: String, password: String) {
        authenticator?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task ->


            }?.addOnSuccessListener {

            }?.addOnCanceledListener {

            }?.addOnFailureListener {

            }
    }


}