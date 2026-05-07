package com.joyline.matatuguide.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Create DataStore instance (attached to Context)
val Context.dataStore by preferencesDataStore(name = "routes")

object RouteStorage {

    private val ROUTES_KEY = stringPreferencesKey("saved_routes")

    // ✅ SAVE A ROUTE
    suspend fun saveRoute(context: Context, route: String) {
        val prefs = context.dataStore.data.first()
        val currentRoutes = prefs[ROUTES_KEY] ?: ""

        val updatedRoutes = if (currentRoutes.isEmpty()) {
            route
        } else {
            "$currentRoutes|$route"
        }

        context.dataStore.edit { settings ->
            settings[ROUTES_KEY] = updatedRoutes
        }
    }

    // ✅ GET ALL ROUTES
    suspend fun getRoutes(context: Context): List<String> {
        val prefs = context.dataStore.data.first()
        val routesString = prefs[ROUTES_KEY] ?: ""

        return if (routesString.isEmpty()) {
            emptyList()
        } else {
            routesString.split("|")
        }
    }

    // ✅ OVERWRITE ROUTES (used for delete)
    suspend fun overwriteRoutes(context: Context, routes: List<String>) {
        val updated = routes.joinToString("|")

        context.dataStore.edit {
            it[ROUTES_KEY] = updated
        }
    }

    // ⭐ OPTIONAL: CLEAR ALL ROUTES
    suspend fun clearRoutes(context: Context) {
        context.dataStore.edit {
            it.remove(ROUTES_KEY)
        }
    }
}