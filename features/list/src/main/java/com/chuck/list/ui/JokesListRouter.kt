package com.chuck.list.ui

import android.os.Bundle
import androidx.navigation.NavController

interface JokesListRouter {
    fun navigateToDetails(navController: NavController, bundle: Bundle)
}