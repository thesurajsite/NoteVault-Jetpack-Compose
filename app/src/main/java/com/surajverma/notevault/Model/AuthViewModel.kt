package com.surajverma.notevault.Model

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.surajverma.notevault.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel(private val profileViewModel: ProfileViewModel): ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val _authState  = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState
    private lateinit var sharedPreferences: SharedPreferences


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

        _authState.value= AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _authState.value= AuthState.Authenticated
                }
                else{
                    _authState.value=
                        AuthState.Error(it.exception?.message ?: "Something Went Wrong")

                }
            }

    }

    fun signup(email: String, password: String, context: Context){

        sharedPreferences = SharedPreferences(context)

        if(email.isEmpty() || password.isEmpty()){
            _authState.value= AuthState.Error("Email or Passowrd can't be Empty")
        }

        _authState.value= AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _authState.value= AuthState.Authenticated

                    Toast.makeText(context, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
                    
                    GlobalScope.launch ( Dispatchers.Main ){
                        delay(3000)
                        // When the signup completes, user profile is created
                        val profile = ProfileModel("Anonymous", "College Name", "12345678", "Branch", "1st Year", "1234567890")
                        profileViewModel.saveProfile(profile, context)
                    }

                }
                else{
                    _authState.value=
                        AuthState.Error(it.exception?.message ?: "Something Went Wrong")

                }
            }

    }

    fun signout(){
        auth.signOut()
        _authState.value= AuthState.Unauthenticated

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