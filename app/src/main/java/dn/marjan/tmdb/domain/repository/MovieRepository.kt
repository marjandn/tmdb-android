package dn.marjan.tmdb.domain.repository

import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.model.MovieResponse
import dn.marjan.tmdb.domain.entity.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<Movie>>

    suspend fun getFirstTenPopularMovies(): DataState<List<Movie>>
    suspend fun getFirstTenUpcomingMovies(): DataState<List<Movie>>
    suspend fun getFirstTenFeaturedMovies(): DataState<List<Movie>>

    fun getFeatureMoves(): Flow<PagingData<Movie>>

       fun searchMovie(query:String):  Flow<PagingData<Movie>>

}