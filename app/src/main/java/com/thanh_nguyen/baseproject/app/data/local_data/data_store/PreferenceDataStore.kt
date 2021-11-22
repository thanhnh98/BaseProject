package com.thanh_nguyen.baseproject.app.data.local_data.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.collect

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("PreferenceDataStore")
class PreferenceDataStore {
    companion object {
        private lateinit var dataStore: DataStore<Preferences>
        operator fun invoke(context: Context){
            dataStore = context.dataStore
        }
    }

    private val ID_KEY = intPreferencesKey("ID_KEY")

    suspend fun getId(id: Int){
        return dataStore.data.collect {

        }
    }




}

