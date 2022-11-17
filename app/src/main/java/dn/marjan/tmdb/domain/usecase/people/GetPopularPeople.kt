package dn.marjan.tmdb.domain.usecase.people

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.usecase.UseCase
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.repository.PeopleRepository
import javax.inject.Inject

class GetPopularPeople @Inject constructor(private val repository: PeopleRepository): UseCase<List<People>, PagingParam>() {
    override suspend fun invoke(param: PagingParam): DataState<List<People>> = repository.getPopularPeople(param)
}