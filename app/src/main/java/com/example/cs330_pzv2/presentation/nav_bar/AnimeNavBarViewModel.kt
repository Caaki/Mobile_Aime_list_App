package com.example.cs330_pzv2.presentation.nav_bar

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeNavBarViewModel @Inject constructor(

) :ViewModel() {
    var tabIndex by mutableStateOf(2)
    private val _state = mutableStateOf(AnimeNavBarState())
    val state: State<AnimeNavBarState> = _state

    init {
        tabIndex = 0
    }

    fun dialog(){
        _state.value = _state.value.copy(
            dialog = true
        )
    }

    fun closeDialog(){
        _state.value = _state.value.copy(
            isMainPage = true,
            isSearching = false,
            localDatabase = false,
            dialog = false,
            isWatched = false

        )
    }

    fun closeDialogSearch(){
        _state.value = _state.value.copy(
            isMainPage = false,
            isSearching = true,
            localDatabase = false,
            dialog = false,
            isWatched = false
        )
    }

    fun setShown(selected: Int) {
        when (selected) {
            0 -> {

                _state.value = _state.value.copy(
                    isMainPage = true,
                    localDatabase = false,
                    isSearching = false,
                    dialog = false,
                    isWatched = false
                )
            }

            1 -> {
                _state.value = _state.value.copy(
                    isMainPage = false,
                    isSearching = false,
                    localDatabase = true,
                    dialog = false,
                    isWatched = false
                )
            }

            2 -> {
                _state.value = _state.value.copy(
                    isMainPage = false,
                    isSearching = false,
                    localDatabase = false,
                    dialog = false,
                    isWatched = true
                )
            }
        }
    }
}