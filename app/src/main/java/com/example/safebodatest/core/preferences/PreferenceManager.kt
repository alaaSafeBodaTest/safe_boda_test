package com.example.safebodatest.core.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager @Inject constructor(private val context: Context) {

    private val preferenceManager: SharedPreferences =
        context.getSharedPreferences("SAFE_BODA", Context.MODE_PRIVATE)

    private fun applyEdit(m: (SharedPreferences.Editor) -> Unit) {
        val editor = preferenceManager.edit()
        m.invoke(editor)
        editor.apply()
    }

    fun putString(key: String, value: String) {
        applyEdit {
            it.putString(key, value)
        }
    }

    fun getString(key: String): String? {
        return if (preferenceManager.contains(key))
            preferenceManager.getString(key, "")
        else
            null
    }

    fun putInt(key: String, value: Int) {
        applyEdit {
            it.putInt(key, value)
        }
    }

    fun getInt(key: String): Int? {
        return if (preferenceManager.contains(key))
            preferenceManager.getInt(key, 0)
        else
            null
    }

    fun putBoolean(key: String, value: Boolean) {
        applyEdit {
            it.putBoolean(key, value)
        }
    }

    fun getBoolean(key: String): Boolean? {
        return if (preferenceManager.contains(key))
            preferenceManager.getBoolean(key, false)
        else
            null
    }

    fun putLong(key: String, value: Long) {
        applyEdit {
            it.putLong(key, value)
        }
    }

    fun getLong(key: String): Long? {
        return if (preferenceManager.contains(key))
            preferenceManager.getLong(key, 0)
        else
            null
    }

    fun putFloat(key: String, value: Float) {
        applyEdit {
            it.putFloat(key, value)
        }
    }

    fun getFloat(key: String): Float? {
        return if (preferenceManager.contains(key))
            preferenceManager.getFloat(key, 0f)
        else
            null
    }

    fun clearValue(key: String){
        applyEdit {
            it.remove(key)
        }
    }

    fun clearAll(){
        applyEdit {
            it.clear()
        }
    }

}