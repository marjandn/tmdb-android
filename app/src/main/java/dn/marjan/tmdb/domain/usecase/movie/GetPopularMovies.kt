package dn.marjan.tmdb.domain.usecase.movie

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.NoParam
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMovies @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<List<Movie>, PagingParam>() {



    override suspend fun invoke(param: PagingParam): DataState<List<Movie>> =
        movieRepository.getPopularMovies(param)
}