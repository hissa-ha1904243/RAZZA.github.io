package com.example.razzashoppingapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "AuthViewModel"
    private val auth by lazy { FirebaseAuth.getInstance() }
    var user by mutableStateOf(auth.currentUser)
        private set

    val db by lazy { Firebase.firestore }
    val userDocumentRef by lazy {db.collection("users")}
    fun createUserEmailPassword(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            user = if (authResult.user != null) authResult.user else null
            Log.v(TAG, user.toString())
        }

//    fun signInWithEmailAndPassWord(email: String, password: String) =
//        viewModelScope.launch(Dispatchers.IO) {
//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener{task ->
//                    if (task.isSuccessful){
//                        user = auth.currentUser
//                        Log.w(TAG, "Sign in with Email Successful")
//                    } else {
//                        user = null
//                        Log.w(TAG, "Sign in with Email Failed")
//                    }
//                }
////            val authResult = auth.signInWithEmailAndPassword(email, password).await()
////            user = if (authResult.user != null) authResult.user else null
////            Log.v(TAG, user.toString())
//
//        }

    fun signInWithEmailAndPassWord(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            user = if (authResult.user != null) authResult.user else null

        }



    fun initLogin(email: String, password: String, ){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                authResult ->
                if (authResult.user != null){
                    db.collection("users").document(authResult.user!!.uid).get().addOnSuccessListener { documentSnapshot ->
                        user = authResult.user
                    }
                } else{
                    user = null
                }
            }    }

    fun signOut() = auth.signOut().also {
        user = auth.currentUser
    }

    fun resetPassword(email: String)  = auth.sendPasswordResetEmail(email)
}
