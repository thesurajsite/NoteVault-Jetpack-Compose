package com.surajverma.notevault.Model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.surajverma.notevault.SharedPreferences

class ProfileViewModel: ViewModel(){

    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy { Firebase.firestore}
    private val userId= auth.currentUser?.uid.toString()
    private lateinit var sharedPreferences: SharedPreferences

    val userProfile = "USER_PROFILE"

    private val _saveStatus = MutableLiveData<Boolean>()
    val saveStatus : LiveData<Boolean> =  _saveStatus

    private val _userProfileData = MutableLiveData<ProfileModel?>()
    val userProfileData : LiveData<ProfileModel?> =  _userProfileData


    fun saveProfile(profileModel: ProfileModel, context: Context){

        sharedPreferences = SharedPreferences(context)


        // Update Profile on Firebase
        // written the complete userId code because it was being used immediately, even before initialization of the userId variable
        db.collection(userProfile).document(auth.currentUser?.uid.toString()).set(profileModel)
            .addOnSuccessListener {
                _saveStatus.postValue(true)

                // Update Profile on sharedPreferences
                sharedPreferences.updateName(profileModel.name.toString())
                sharedPreferences.updateCollege(profileModel.collegeName.toString())
                sharedPreferences.updateEnrollment(profileModel.enrollment.toString())
                sharedPreferences.updateBranch(profileModel.branch.toString())
                sharedPreferences.updateYear(profileModel.year.toString())
                sharedPreferences.updatePhone(profileModel.phone.toString())

            }
            .addOnFailureListener {
                Toast.makeText(context, "Some went wrong", Toast.LENGTH_SHORT).show()
            }

    }

    fun getProfile(context: Context){

    //    Toast.makeText(context, "Profile Fetched from SharedPreferences", Toast.LENGTH_SHORT).show()

        sharedPreferences = SharedPreferences(context)

        val name = sharedPreferences.getName()
        val college = sharedPreferences.getCollege()
        val enrollment = sharedPreferences.getEnrollment()
        val branch = sharedPreferences.getBranch()
        val year = sharedPreferences.getYear()
        val phone = sharedPreferences.getPhone()

        val profile = ProfileModel(name, college, enrollment, branch, year, phone)
        _userProfileData.postValue(profile)


    }

    fun getProfileOnLogin(context: Context){

      //  Toast.makeText(context, "Profile Fetched from Firebase and saved to SharedPreferences", Toast.LENGTH_SHORT).show()

        sharedPreferences = SharedPreferences(context)

        db.collection(userProfile).document(auth.currentUser?.uid.toString()).get()
            .addOnSuccessListener {
                val name = it.get("name").toString()
                val collegeName=it.get("collegeName").toString()
                val enrollment=it.get("enrollment").toString()
                val branch=it.get("branch").toString()
                val year=it.get("year").toString()
                val phone=it.get("phone").toString()

                // update sharedPreferences
                sharedPreferences.updateName(name)
                sharedPreferences.updateCollege(collegeName)
                sharedPreferences.updateEnrollment(enrollment)
                sharedPreferences.updateBranch(branch)
                sharedPreferences.updateYear(year)
                sharedPreferences.updatePhone(phone)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error fetching profile", Toast.LENGTH_SHORT).show()
            }
    }




}