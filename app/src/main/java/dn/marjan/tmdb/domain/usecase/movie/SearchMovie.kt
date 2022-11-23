package dn.marjan.tmdb.domain.usecase.movie

import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovie @Inject constructor(private val movieRepository: MovieRepository) {

      operator fun invoke(query: String): Flow<PagingData<Movie>> =
        movieRepository.searchMovie(query)
}