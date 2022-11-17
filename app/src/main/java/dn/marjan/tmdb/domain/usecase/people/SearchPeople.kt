package dn.marjan.tmdb.domain.usecase.people

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.repository.PeopleRepository
import javax.inject.Inject

class SearchPeople  @Inject constructor(private val repository: PeopleRepository): UseCase<List<People>, SearchParam>() {
    override suspend fun invoke(param: SearchParam): DataState<List<People>> = repository.searchPeople(param)
}