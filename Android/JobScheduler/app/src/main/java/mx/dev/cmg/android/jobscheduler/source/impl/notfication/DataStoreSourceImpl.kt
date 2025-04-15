package mx.dev.cmg.android.jobscheduler.source.impl.notfication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "notifications")

class DataStoreSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStoreSource {

    private val WELCOME_ALREADY_SHOWN = booleanPreferencesKey("welcome_notification_shown")
    private val LOGIN = booleanPreferencesKey("login")

    override suspend fun welcomeNotificationShown() =
        context.dataStore.data.map { preferences ->
            preferences[WELCOME_ALREADY_SHOWN] ?: false
        }

    override suspend fun setWelcomeNotificationShown() {
        context.dataStore.edit { notifications ->
            notifications[WELCOME_ALREADY_SHOWN] = true
        }
    }

    override suspend fun setLogin(logged: Boolean): Flow<Boolean> {
        context.dataStore.edit {
            it[LOGIN] = logged
        }

        return context.dataStore.data.map { preferences ->
            preferences[LOGIN] ?: false
        }
    }

    override suspend fun isLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[LOGIN] ?: false
        }
    }
}