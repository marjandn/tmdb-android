package dn.marjan.tmdb.data.datasources.remote.services

import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.client.RestClient
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.parser.JsonParser
import dn.marjan.tmdb.data.model.PeopleResponse
import okhttp3.ResponseBody
import javax.inject.Inject

interface PeopleRemoteDataSource {
    suspend fun getPopularPeople(pagingParam: PagingParam): PeopleResponse

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

    override suspend fun searchPeople(searchParam: SearchParam): PeopleResponse {
        try {
            val response: ResponseBody = restClient.getRequest("search/person", searchParam.toJson())
            return jsonParser.fromJson(response.string(), PeopleResponse::class.java)
        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }
}