package com.example.gamehok.data.repository


import com.example.gamehok.data.model.GamesList
import com.example.gamehok.data.model.TournamentsList
import com.example.seekhoassignment.utils.network.ApiState
import kotlinx.coroutines.flow.Flow


interface GameHokeRepository{

    suspend fun fetchGamesList() : Flow<ApiState<GamesList>>

    suspend fun fetchTournamentsList() : Flow<ApiState<TournamentsList>>

}
