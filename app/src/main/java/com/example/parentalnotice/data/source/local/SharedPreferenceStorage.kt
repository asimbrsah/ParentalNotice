package com.example.parentalnotice.data.source.local

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import com.example.parentalnotice.Constants
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface SharedPreferenceImpl {

    var detailPageId: Int

    fun clearAllPreference()
}

@Singleton
class SharedPreferenceStorage(private val context: Context) : SharedPreferenceImpl {

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    override var detailPageId by IntegerPreference(
        prefs, Constants.SHARED_PREF_PARAM_PAGE_ID, 0
    )

    override fun clearAllPreference() {
        prefs.value.edit().clear().apply()
    }
}

class BooleanPreference(
    private val preference: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: Boolean

) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preference.value.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preference.value.edit {
            putBoolean(name, value)
        }
    }
}


class StringPreference(
    private val preference: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String

) : ReadWriteProperty<Any, String> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preference.value.getString(name, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        preference.value.edit {
            putString(name, value)
        }
    }
}

class IntegerPreference(
    private val preference: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: Int

) : ReadWriteProperty<Any, Int> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return preference.value.getInt(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        preference.value.edit {
            putInt(name, value)
        }
    }
}