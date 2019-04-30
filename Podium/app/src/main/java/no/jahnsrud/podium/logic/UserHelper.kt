package no.jahnsrud.podium.logic

import com.google.firebase.auth.FirebaseAuth

class UserHelper {

    fun isLoggedIn() : Boolean {
        var mAuth = FirebaseAuth.getInstance();
        if (mAuth.currentUser != null) {

            return true
        }

        return false

    }

    fun getLoggedInEmail() : String {
        var mAuth = FirebaseAuth.getInstance();
        if (mAuth.currentUser != null) {

            return mAuth.currentUser!!.email.toString()
        }

        return ""
    }

    fun isInputValid(email: String, password:String) : Boolean {

        if (email.length == 0 || password.length == 0) {

            return false
        } else {
            return true
        }


    }

    fun signOut() {
        var mAuth = FirebaseAuth.getInstance();
        mAuth.signOut()
    }

}