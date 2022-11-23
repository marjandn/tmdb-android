package dn.marjan.tmdb.domain.usecase.tvshow

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.domain.repository.TvshowRepository
import javax.inject.Inject

class GetFirstTenPopularTvshow @Inject constructor(private val tvshowRepository: TvshowRepository) {

    suspend operator fun  invoke(): DataState<List<Tvshow>> = tvshowRepository.getFirstTenPopularTvshow()
}