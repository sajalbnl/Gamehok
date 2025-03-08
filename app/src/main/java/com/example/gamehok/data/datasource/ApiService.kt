package com.example.gamehok.data.datasource

import com.example.gamehok.data.model.GamesList
import com.example.gamehok.data.model.TournamentsList
import retrofit2.http.GET

interface ApiService {

    @GET("games")
    suspend fun getGamesList(): GamesList

    @GET("tournaments")
    suspend fun getTournamentsList() : TournamentsList
}