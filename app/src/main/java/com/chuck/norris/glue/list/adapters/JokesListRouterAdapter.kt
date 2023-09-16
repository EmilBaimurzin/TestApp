package com.chuck.norris.glue.list.adapters

import android.os.Bundle
import androidx.navigation.NavController
import com.chuck.list.ui.FragmentJokesListDirections
import com.chuck.list.ui.JokesListRouter
import com.chuck.norris.R

class JokesListRouterAdapter(): JokesListRouter {
    override fun navigateToDetails(navController: NavController, bundle: Bundle) {
        navController.navigate(R.id.action_fragmentJokesList_to_fragmentDetails, bundle)
    }
}