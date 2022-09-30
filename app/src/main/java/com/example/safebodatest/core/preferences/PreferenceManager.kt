package com.example.safebodatest.core.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.safebodatest.BuildConfig
import javax.inject.Inject

class PreferenceManager @Inject constructor(context: Context) {


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
            preferenceManager.getInt(key, -1)
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

    init {
        preferenceManager = context.getSharedPreferences(BuildConfig.APPLICATION_ID,
            Context.MODE_PRIVATE
        )
    }
    companion object {

        //region  Variables

        private lateinit var preferenceManager: SharedPreferences
        /*

     Without volatile the code doesn't work correctly with multiple threads.
      The volatile prevents memory writes from being re-ordered,
       making it impossible for other threads to read uninitialized fields of your singleton through
       the singleton's pointer.
    */

        @Volatile
        private var instance: PreferenceManager? = null

        //endregion

        // region Setter & Getter

        @Synchronized
        fun getInstance(context: Context): PreferenceManager {
            val checkInstance = instance
            if (checkInstance != null) {
                return checkInstance
            }
            return synchronized(this) {
                val checkInstanceAgain = instance
                if (checkInstanceAgain != null) {
                    checkInstanceAgain
                } else {
                    val created = PreferenceManager(context)
                    instance = created
                    created
                }
            }
        }
    }

}