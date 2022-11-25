package dn.marjan.tmdb.domain.repository

import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.entity.MovieCredits
import dn.marjan.tmdb.domain.entity.MovieDetails
import dn.marjan.tmdb.domain.entity.MoviePictures
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<Movie>>

    suspend fun getFirstTenPopularMovies(): DataState<List<Movie>>
    suspend fun getFirstTenUpcomingMovies(): DataState<List<Movie>>
    suspend fun getFirstTenFeaturedMovies(): DataState<List<Movie>>

    fun getFeatureMoves(): Flow<PagingData<Movie>>

    fun searchMovie(query: String): Flow<PagingData<Movie>>

    suspend fun getMoviePrimaryDetails(movieDetailsParam: MovieDetailsParam): DataState<MovieDetails>
    suspend fun getMoviePictures(movieDetailsParam: MovieDetailsParam): DataState<List<MoviePictures>>
    suspend fun getMovieCredits(movieDetailsParam: MovieDetailsParam): DataState<List<MovieCredits>>

}