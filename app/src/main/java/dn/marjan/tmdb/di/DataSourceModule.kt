package dn.marjan.tmdb.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dn.marjan.tmdb.data.datasources.remote.client.RestClient
import dn.marjan.tmdb.data.datasources.remote.client.RetrofitBase
import dn.marjan.tmdb.data.datasources.remote.client.TMDBServices
import dn.marjan.tmdb.data.datasources.remote.parser.JsonParser
import dn.marjan.tmdb.data.datasources.remote.services.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideMovieDataSource(
        restClient: RestClient,
        jsonParser: JsonParser
    ): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(restClient = restClient, jsonParser = jsonParser)


    @Singleton
    @Provides
    fun provideTvshowDataSource(
        restClient: RestClient,
        jsonParser: JsonParser
    ): TvshowRemoteDataSource =
        TvshowRemoteDataSourceImpl(restClient, jsonParser)

    @Singleton
    @Provides
    fun providePeopleDataSource(
        restClient: RestClient,
        jsonParser: JsonParser
    ): PeopleRemoteDataSource =
        PeopleRemoteDataSourceImpl(restClient, jsonParser)
}