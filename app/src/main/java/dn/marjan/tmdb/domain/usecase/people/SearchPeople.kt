package dn.marjan.tmdb.domain.usecase.people

import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPeople  @Inject constructor(private val peopleRepository: PeopleRepository) {

    operator fun invoke(query: String): Flow<PagingData<People>> = peopleRepository.searchPeople(query)
}