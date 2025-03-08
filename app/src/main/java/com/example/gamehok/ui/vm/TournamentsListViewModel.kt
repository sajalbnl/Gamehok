package com.example.gamehok.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamehok.data.model.TournamentsList
import com.example.gamehok.data.repository.GameHokeRepository
import com.example.seekhoassignment.utils.network.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TournamentsListViewModel @Inject constructor(private val repository: GameHokeRepository ) : ViewModel() {

    private val _tournamentsList = MutableStateFlow<ApiState<TournamentsList>>(ApiState.Loading)
    val tournamentsList : StateFlow<ApiState<TournamentsList>> = _tournamentsList.asStateFlow()

    fun fetchTournamentsList(){
        viewModelScope.launch{
            repository.fetchTournamentsList().let {
                it.collect{
                    _tournamentsList.value = it
                }
            }
        }
    }

}