package dn.marjan.tmdb.domain.usecase.movie

import androidx.paging.PagingData
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMovies @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(): Flow<PagingData<Movie>> =
        movieRepository.getPopularMovies()

}