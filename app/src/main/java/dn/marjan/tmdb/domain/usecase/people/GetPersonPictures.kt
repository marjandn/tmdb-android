package dn.marjan.tmdb.domain.usecase.people

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import dn.marjan.tmdb.data.datasources.remote.parameters.PersonDetailsParam
import dn.marjan.tmdb.domain.entity.PersonPictures
import dn.marjan.tmdb.domain.repository.PeopleRepository
import javax.inject.Inject

class GetPersonPictures @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    suspend operator fun invoke(personId: Int): List<String> {
        val response = peopleRepository.getPersonPictures(
            PersonDetailsParam(personId)
        )

        return if (response is DataState.DataSuccessState) response.data.map { it.picturePath }
        else emptyList()
    }

}