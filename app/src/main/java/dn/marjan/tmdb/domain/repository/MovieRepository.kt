package dn.marjan.tmdb.domain.repository

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.model.MovieResponse
import dn.marjan.tmdb.domain.entity.Movie


interface MovieRepository {

    suspend fun getPopularMovies(pageParam: PagingParam): DataState<List<Movie>>

    suspend fun getUpComingMovies(pageParam: PagingParam): DataState<List<Movie>>
    suspend fun getFeatureMoves(pageParam: PagingParam): DataState<List<Movie>>

    suspend fun searchMovie(searchParam: SearchParam): DataState<List<Movie>>

}