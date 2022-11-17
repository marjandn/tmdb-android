package dn.marjan.tmdb.domain.usecase.tvshow

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.domain.repository.TvshowRepository
import javax.inject.Inject

class GetPopularTvshows @Inject constructor(private val tvshowRepository: TvshowRepository): UseCase<List<Tvshow>, PagingParam>() {
    override suspend fun invoke(param: PagingParam): DataState<List<Tvshow>> = tvshowRepository.getPopularTvshows(pageParam = param)
}