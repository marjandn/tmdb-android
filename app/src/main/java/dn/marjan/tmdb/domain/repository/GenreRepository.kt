package dn.marjan.tmdb.domain.repository

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.domain.entity.Genre

interface GenreRepository {
    suspend fun getMovieGenres(): DataState<List<Genre>>

    suspend fun getTvshowGenres(): DataState<List<Genre>>
}