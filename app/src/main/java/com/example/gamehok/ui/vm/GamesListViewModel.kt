package com.example.gamehok.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamehok.data.model.GamesList
import com.example.gamehok.data.repository.GameHokeRepository
import com.example.seekhoassignment.utils.network.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesListViewModel @Inject constructor(private val repository: GameHokeRepository) : ViewModel(){

    private val _gamesList = MutableStateFlow<ApiState<GamesList>>(ApiState.Loading)
    val gamesList: StateFlow<ApiState<GamesList>> = _gamesList

    fun fetchGamesList(){
        viewModelScope.launch{
            repository.fetchGamesList().let{
                it.collect{
                    _gamesList.value = it
                }
            }
        }
    }


}