package th.ac.up.melondev.new_farm_management.data

import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import th.ac.up.melondev.new_farm_management.R
import th.ac.up.melondev.new_farm_management.data.listener.GoogleSignInEventListener

class FirebaseAuthenticationClient {

    companion object {
        fun createService(): FirebaseAuthenticationClient=
            FirebaseAuthenticationClient().createService()
    }

    private val RCSIGNIN = 56000
    private var googleSigninClient: GoogleSignInClient? = null
    private var authenticator: FirebaseAuth? = null

    fun createService(): FirebaseAuthenticationClient {
        this.authenticator = FirebaseAuth.getInstance()
        return this
    }

    fun signinByGoogle(fragment: FragmentActivity) {
        googleSigninClient = GoogleSignIn.getClient(
            fragment, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(fragment.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        )
        fragment.startActivityForResult(googleSigninClient?.signInIntent, RCSIGNIN)
    }

    fun activateAccountFromGoogle(requestCode: Int, data: Intent?, listener: GoogleSignInEventListener) {
        if (requestCode == RCSIGNIN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)?.let { account ->
                    firebaseAuthWithGoogle(account,listener)
                } ?: run {
                    listener.onError()
                }
            } catch (e: ApiException) {
                Log.e("RE",e.localizedMessage)

                listener.onError()
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount, listener: GoogleSignInEventListener) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        authenticator?.signInWithCredential(credential)
            ?.addOnCompleteListener {
                listener.onSignInSuccess()
            }
    }

    fun signout() {
        authenticator?.signOut()
        googleSigninClient?.signOut()?.addOnCompleteListener {
            googleSigninClient = null
        }
    }

}