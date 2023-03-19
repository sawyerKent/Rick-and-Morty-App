package com.skent.lib_data.di

import android.content.Context
import androidx.room.Room
import com.skent.lib_data.data.local.RickAndMortyDatabase
import com.skent.lib_data.data.local.dao.CharacterDao
import com.skent.lib_data.data.local.dao.LocationDao
import com.skent.lib_data.data.local.dao.RemoteKeyDao
import com.skent.lib_data.data.remote.RemoteDataSourceImpl
import com.skent.lib_data.data.remote.RickAndMortyApiService
import com.skent.lib_data.data.repository.RickAndMortyRepositoryImpl
import com.skent.lib_data.domain.repository.RemoteDataSource
import com.skent.lib_data.domain.repository.RickAndMortyRepository
import com.skent.lib_data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        @Provides
        @Singleton
        fun provideAPI() = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApiService::class.java)

        @Provides
        @Singleton
        fun provideRemoteDataSource(
            apiService: RickAndMortyApiService,
            rickAndMortyDatabase: RickAndMortyDatabase
        ): RemoteDataSource {
            return RemoteDataSourceImpl(apiService, rickAndMortyDatabase)
        }


        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): RickAndMortyDatabase =
            Room.databaseBuilder(
                context,
                RickAndMortyDatabase::class.java,
                Constants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

        @Singleton
        @Provides
        fun provideCharacterDao(rickAndMortyDatabase: RickAndMortyDatabase):
                CharacterDao = rickAndMortyDatabase.getCharacterDao()

        @Singleton
        @Provides
        fun provideLocationDao(rickAndMortyDatabase: RickAndMortyDatabase):
                LocationDao = rickAndMortyDatabase.getLocationDao()

        @Singleton
        @Provides
        fun provideRemoteKeysDao(rickAndMortyDatabase: RickAndMortyDatabase):
                RemoteKeyDao = rickAndMortyDatabase.getRemoteKeysDao()


        @Singleton
        @Provides
        fun provideRepo(
            apiService: RickAndMortyApiService,
            rickAndMortyDatabase: RickAndMortyDatabase,
            remoteDataSource: RemoteDataSource
        ):
                RickAndMortyRepository =
            RickAndMortyRepositoryImpl(apiService, rickAndMortyDatabase, remoteDataSource)
}
