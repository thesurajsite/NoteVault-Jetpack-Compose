package com.surajverma.notevault

import android.content.Context
import android.content.SharedPreferences


class SharedPreferences(private val context: Context): MyInterface {

    override fun getName(): String? {
        val pref : SharedPreferences =getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        return pref.getString("name", "Anonymous")
    }

    override fun updateName(newName: String){
        val pref: SharedPreferences = getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        val editor =pref.edit()
        editor.putString("name", newName)

    }

    override fun getCollege(): String? {
        val pref : SharedPreferences =getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        return pref.getString("college", "College Name")
    }

    override fun updateCollege(newCollege: String){
        val pref: SharedPreferences = getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        val editor =pref.edit()
        editor.putString("college", newCollege)

    }

    override fun getEnrollment(): String? {
        val pref : SharedPreferences =getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        return pref.getString("enrollment", "12345678")
    }

    override fun updateEnrollment(newEnrollment: String){
        val pref: SharedPreferences = getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        val editor =pref.edit()
        editor.putString("enrollment", newEnrollment)
    }

    override fun getBranch(): String? {
        val pref : SharedPreferences =getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        return pref.getString("branch", "Branch")
    }

    override fun updateBranch(newBranch: String){
        val pref: SharedPreferences = getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        val editor =pref.edit()
        editor.putString("branch", newBranch)
    }

    override fun getYear(): String? {
        val pref : SharedPreferences =getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        return pref.getString("year", "1st Year")
    }

    override fun updateYear(newYear: String){
        val pref: SharedPreferences = getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        val editor =pref.edit()
        editor.putString("year", newYear)
    }

    override fun getPhone(): String? {
        val pref : SharedPreferences =getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        return pref.getString("phone", "1st Year")
    }

    override fun updatePhone(newPhone: String){
        val pref: SharedPreferences = getSharedPreferences("NOTEVAULT", Context.MODE_PRIVATE)
        val editor =pref.edit()
        editor.putString("phone", newPhone)
    }

    fun getSharedPreferences(s: String, modePrivate: Int): SharedPreferences {
        return context.getSharedPreferences(s, modePrivate)
    }
}