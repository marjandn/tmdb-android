package dn.marjan.tmdb.data.datasources.remote.services

import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.client.RestClient
import dn.marjan.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.PersonDetailsParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.parser.JsonParser
import dn.marjan.tmdb.data.model.PeopleResponse
import dn.marjan.tmdb.data.model.PersonDetailsResponse
import dn.marjan.tmdb.data.model.PersonPicturesResponse
import okhttp3.ResponseBody
import javax.inject.Inject

interface PeopleRemoteDataSource {
    suspend fun getPopularPeople(pagingParam: PagingParam): PeopleResponse

    suspend fun getPersonDetails(detailsParam: PersonDetailsParam): PersonDetailsResponse
    suspend fun getPersonPictures(detailsParam: PersonDetailsParam): PersonPicturesResponse

    suspend fun searchPeople(searchParam: SearchParam): PeopleResponse

}

class PeopleRemoteDataSourceImpl @Inject constructor(
    private val restClient: RestClient,
    private val jsonParser: JsonParser
) : PeopleRemoteDataSource {

    override suspend fun getPopularPeople(pagingParam: PagingParam): PeopleResponse {
        try {
            val response: ResponseBody = restClient.getRequest("person/popular", pagingParam.toJson())
            return jsonParser.fromJson(response.string(), PeopleResponse::class.java)
        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getPersonDetails(detailsParam: PersonDetailsParam): PersonDetailsResponse {
        try {
            val response: ResponseBody = restClient.getRequest("person/${detailsParam.personId}")
            return jsonParser.fromJson(response.string(), PersonDetailsResponse::class.java)
        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getPersonPictures(detailsParam: PersonDetailsParam): PersonPicturesResponse {
        try {
            val response: ResponseBody = restClient.getRequest("person/${detailsParam.personId}/images")
            return jsonParser.fromJson(response.string(), PersonPicturesResponse::class.java)
        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }

    override suspend fun searchPeople(searchParam: SearchParam): PeopleResponse {
        try {
            val response: ResponseBody = restClient.getRequest("search/person", searchParam.toJson())
            return jsonParser.fromJson(response.string(), PeopleResponse::class.java)
        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }
}