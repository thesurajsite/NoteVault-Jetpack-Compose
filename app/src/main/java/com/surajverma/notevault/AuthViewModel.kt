package com.surajverma.notevault

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _authState  = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }
        else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String ){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value= AuthState.Error("Email or Passowrd can't be Empty")

        }

        _authState.value=AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _authState.value=AuthState.Authenticated
                }
                else{
                    _authState.value= AuthState.Error(it.exception?.message?: "Something Went Wrong")

                }
            }

    }

    fun signup(email: String, password: String ){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value= AuthState.Error("Email or Passowrd can't be Empty")

        }

        _authState.value=AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _authState.value=AuthState.Authenticated
                }
                else{
                    _authState.value= AuthState.Error(it.exception?.message?: "Something Went Wrong")

                }
            }

    }

    fun signout(){
        auth.signOut()
        _authState.value=AuthState.Unauthenticated

    }
}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated: AuthState()
    object Loading : AuthState()

    data class Error(
        val message: String
    ) : AuthState()


}