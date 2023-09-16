package com.chuck.list.ui

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.chuck.list.domain.JokesListUseCase
import com.chuck.list.domain.entities.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesListViewModel @Inject constructor(
    private val useCase: JokesListUseCase,
    private val router: JokesListRouter
): ViewModel() {
    private val _list = MutableLiveData<List<Joke>>(emptyList())
    val list: LiveData<List<Joke>> = _list

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    var errorCallback: (() -> Unit)? = null

    fun navigateToDetails(navController: NavController, bundle: Bundle) {
        router.navigateToDetails(navController, bundle)
    }
    fun getJoke() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val joke = useCase.getJoke()
            if (joke != null) {
                val currentList = _list.value!!.toMutableList()
                currentList.add(0, joke)
                _list.postValue(currentList)
            } else {
                errorCallback?.invoke()
            }
            _isLoading.postValue(false)
        }
    }
}