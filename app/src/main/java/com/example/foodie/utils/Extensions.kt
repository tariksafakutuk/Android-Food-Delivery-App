package com.example.foodie.utils

import android.view.View
import androidx.navigation.Navigation

fun Navigation.changePage(view: View, id: Int) {
    findNavController(view).navigate(id)
}