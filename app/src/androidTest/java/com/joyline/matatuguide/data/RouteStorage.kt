package com.joyline.matatuguide.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Create DataStore instance
val Context.dataStore by preferencesDataStore(name = "routes")

object RouteStorage {

    private val ROUTES_KEY = stringPreferencesKey("saved_routes")

    // SAVE ROUTE
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

    // GET ROUTES
    suspend fun getRoutes(context: Context): List<String> {
        val prefs = context.dataStore.data.first()
        val routesString = prefs[ROUTES_KEY] ?: ""

        return if (routesString.isEmpty()) {
            emptyList()
        } else {
            routesString.split("|")
        }
    }
}