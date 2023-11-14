package com.example.foodie.utils

import android.content.Context
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.changePage(view: View, id: Int) {
    findNavController(view).navigate(id)
}

fun Navigation.changePage(view: View, id: NavDirections) {
    findNavController(view).navigate(id)
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("account")