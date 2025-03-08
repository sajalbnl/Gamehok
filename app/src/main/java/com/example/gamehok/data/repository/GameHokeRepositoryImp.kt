package com.example.gamehok.data.repository

import com.example.gamehok.data.datasource.ApiService
import com.example.gamehok.data.model.GamesList
import com.example.gamehok.data.model.TournamentsList
import com.example.seekhoassignment.utils.network.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GameHokRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GameHokeRepository  {
    override suspend fun fetchGamesList(): Flow<ApiState<GamesList>> = flow {
        try {
            val result = apiService.getGamesList()
            emit(ApiState.Success(result))
        } catch (e: HttpException) {
            val jObjError: JSONObject = JSONObject(e.response()!!.errorBody()!!.string())
            emit(
                ApiState.Error(jObjError.optString("message"), e)
            )
        } catch (e: IOException) {
            emit(
                ApiState.Error("Couldn't reach server, check your internet connection.", e)
            )
        }

    }

    override suspend fun fetchTournamentsList(): Flow<ApiState<TournamentsList>> = flow {
        try {
            val result = apiService.getTournamentsList()
            emit(ApiState.Success(result))
        } catch (e: HttpException) {
            val jObjError: JSONObject = JSONObject(e.response()!!.errorBody()!!.string())
            emit(
                ApiState.Error(jObjError.optString("message"), e)
            )
        } catch (e: IOException) {
            emit(
                ApiState.Error("Couldn't reach server, check your internet connection.", e)
            )
        }
    }
}