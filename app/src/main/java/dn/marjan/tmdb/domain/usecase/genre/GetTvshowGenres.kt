package dn.marjan.tmdb.domain.usecase.genre

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.NoParam
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.domain.entity.Genre
import dn.marjan.tmdb.domain.repository.GenreRepository
import javax.inject.Inject

class GetTvshowGenres  @Inject constructor(private val repository: GenreRepository): UseCase<List<Genre>, NoParam>() {
    override suspend fun invoke(param: NoParam): DataState<List<Genre>> = repository.getTvshowGenres()
}