package dn.marjan.tmdb.domain.usecase.tvshow

import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.domain.repository.TvshowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeaturedTvshows @Inject constructor(private val tvshowRepository: TvshowRepository) {

    operator fun invoke(): Flow<PagingData<Tvshow>> = tvshowRepository.getFeaturedTvshows()
}