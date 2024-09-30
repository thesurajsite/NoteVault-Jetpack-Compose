package com.surajverma.notevault

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileViewModel: ViewModel(){

    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy { Firebase.firestore}
    private val userId= auth.currentUser?.uid.toString()

    val userProfile = "USER_PROFILE"

    private val _saveStatus = MutableLiveData<Boolean>()
    val saveStatus : LiveData<Boolean> =  _saveStatus

    private val _userProfileData = MutableLiveData<ProfileModel?>()
    val userProfileData : LiveData<ProfileModel?> =  _userProfileData


    fun saveProfile(profileModel: ProfileModel, context: Context){

     //   val map = mutableMapOf<String,String>()
//        map.put("NAME", profileModel.name!!)
//        map.put("COLLEGE_NAME", profileModel.collegeName!!)
//        map.put("ENROLLMENT", profileModel.enrollment!!)
//        map.put("BRANCH", profileModel.branch!!)
//        map.put("YEAR", profileModel.year!!)
//        map.put("PHONE", profileModel.phone!!)

        db.collection(userProfile).document(userId).set(profileModel)
            .addOnSuccessListener {
                Toast.makeText(context, "Profile Edited", Toast.LENGTH_SHORT).show()
                _saveStatus.postValue(true)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Some went wrong", Toast.LENGTH_SHORT).show()
            }


    }

    fun getProfile(context: Context){
        db.collection(userProfile).document(userId).get()
            .addOnSuccessListener {

                val name = it.get("name").toString()
                val collegeName=it.get("collegeName").toString()
                val enrollment=it.get("enrollment").toString()
                val branch=it.get("branch").toString()
                val year=it.get("year").toString()
                val phone=it.get("phone").toString()


                val profile = ProfileModel(name, collegeName, enrollment, branch, year, phone)


              //  val profile = it.toObject(ProfileModel::class.java)
                _userProfileData.postValue(profile)
//
//                Toast.makeText(context, "$name $collegeName $enrollment $branch $year $phone", Toast.LENGTH_SHORT).show()



            }
            .addOnFailureListener {
                Toast.makeText(context, "Error fetching profile", Toast.LENGTH_SHORT).show()
            }


    }




}