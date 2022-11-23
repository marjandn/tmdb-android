package dn.marjan.tmdb.domain.usecase.people

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.repository.PeopleRepository
import javax.inject.Inject

class GetFirstTenPopularPeople @Inject constructor(private val peopleRepository: PeopleRepository) {

    suspend operator fun invoke(): DataState<List<People>> = peopleRepository.getFirstTenPopularPeople()
}