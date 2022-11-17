package dn.marjan.tmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dn.marjan.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import dn.marjan.tmdb.data.datasources.remote.services.PeopleRemoteDataSource
import dn.marjan.tmdb.data.datasources.remote.services.TvshowRemoteDataSource
import dn.marjan.tmdb.data.repositories.MovieRepositoryImpl
import dn.marjan.tmdb.data.repositories.PeopleRepositoryImpl
import dn.marjan.tmdb.data.repositories.TvshowRepositoryImpl
import dn.marjan.tmdb.domain.repository.MovieRepository
import dn.marjan.tmdb.domain.repository.PeopleRepository
import dn.marjan.tmdb.domain.repository.TvshowRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource = movieRemoteDataSource)

    @Singleton
    @Provides
    fun provideTvshowRepository(tvshowRemoteDataSource: TvshowRemoteDataSource): TvshowRepository =
        TvshowRepositoryImpl(tvshowRemoteDataSource)

    @Singleton
    @Provides
    fun providePeopleRepository(peopleRemoteDataSource: PeopleRemoteDataSource): PeopleRepository =
        PeopleRepositoryImpl(peopleRemoteDataSource)
}