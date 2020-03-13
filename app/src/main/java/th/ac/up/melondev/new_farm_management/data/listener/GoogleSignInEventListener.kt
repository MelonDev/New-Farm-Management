package th.ac.up.melondev.new_farm_management.data.listener

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface GoogleSignInEventListener {

    fun onSignInSuccess()
    fun onError()
}