package no.jahnsrud.podium

import com.google.firebase.auth.FirebaseAuth

class UserHelper {

    fun isLoggedIn() : Boolean {
        var mAuth = FirebaseAuth.getInstance();
        if (mAuth != null) {
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