package dn.marjan.tmdb.domain.usecase

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class GetUpComingMovies @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<List<Movie>, PagingParam>() {


    override suspend fun invoke(param: PagingParam): DataState<List<Movie>> =movieRepository.getUpComingMovies(param)
}