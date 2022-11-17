package dn.marjan.tmdb.domain.usecase.tvshow

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.domain.repository.TvshowRepository
import javax.inject.Inject

class SearchTvshow @Inject constructor(private val tvshowRepository: TvshowRepository): UseCase<List<Tvshow>, SearchParam>() {

    override suspend fun invoke(param: SearchParam): DataState<List<Tvshow>> = tvshowRepository.searchTvshows(searchParam =  param)
}