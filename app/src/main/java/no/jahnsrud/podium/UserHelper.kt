package no.jahnsrud.podium

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



class UserHelper {

    fun isLoggedIn() : Boolean {
        var mAuth = FirebaseAuth.getInstance();
        if (mAuth.currentUser != null) {

            return true
        }

        return false

    }

    fun isInputValid(email: String, password:String) : Boolean {

        if (email.length == 0 || password.length == 0) {

            return false
        } else {
            return true
        }


    }

}