package com.vama.topalbums.dependencyinjection

import android.content.Context
import com.vama.topalbums.data.AlbumDataRepository
import com.vama.topalbums.data.local.AlbumLocalDataSource
import com.vama.topalbums.data.local.AlbumLocalSource
import com.vama.topalbums.data.local.model.AlbumDatabaseModel
import com.vama.topalbums.data.local.model.GenreDatabaseModel
import com.vama.topalbums.data.remote.AlbumRemoteDataSource
import com.vama.topalbums.data.remote.AlbumRemoteSource
import com.vama.topalbums.data.remote.AlbumService
import com.vama.topalbums.data.remote.createRestClient
import com.vama.topalbums.domain.NetworkConnectivity
import com.vama.topalbums.domain.mapper.*
import com.vama.topalbums.domain.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@Module
@InstallIn(ViewModelComponent::class)
object AlbumDataModule {

    @Provides
    fun providesAlbumService(): AlbumService = createRestClient(AlbumService::class.java)

    @Provides
    fun providesRealmConfiguration() = RealmConfiguration.Builder(
        schema = setOf(AlbumDatabaseModel::class, GenreDatabaseModel::class)
    ).build()

    @Provides
    fun providesRealmDatabase(realmConfiguration: RealmConfiguration) =
        Realm.open(realmConfiguration)

    @Provides
    fun providesAlbumLocalSource(realm: Realm): AlbumLocalSource = AlbumLocalDataSource(realm)

    @Provides
    fun providesAlbumRemoteSource(albumService: AlbumService): AlbumRemoteSource =
        AlbumRemoteDataSource(albumService)

    @Provides
    fun providesAlbumRepository(
        albumRemoteSource: AlbumRemoteSource,
        albumLocalSource: AlbumLocalSource,
        albumApiModelToAlbumMapper: AlbumApiModelToAlbumMapper,
        albumDatabaseModelToAlbumMapper: AlbumDatabaseModelToAlbumMapper,
        albumsToDatabaseMapper: AlbumsToDatabaseMapper,
        networkConnectivity: NetworkConnectivity
    ): AlbumRepository = AlbumDataRepository(
        albumRemoteSource,
        albumLocalSource,
        albumApiModelToAlbumMapper,
        albumDatabaseModelToAlbumMapper,
        albumsToDatabaseMapper,
        networkConnectivity
    )

    @Provides
    fun providesGenreApiToGenreMapper() = GenreApiToGenreMapper()

    @Provides
    fun providesAlbumApiModelToAlbumMapper(
        genreApiToGenreMapper: GenreApiToGenreMapper
    ) = AlbumApiModelToAlbumMapper(genreApiToGenreMapper)

    @Provides
    fun providesGenreDatabaseModelToGenreMapper() = GenreDatabaseModelToGenreMapper()

    @Provides
    fun providesAlbumDatabaseModelToAlbumMapper(
        genreDatabaseModelToGenreMapper: GenreDatabaseModelToGenreMapper
    ) = AlbumDatabaseModelToAlbumMapper(genreDatabaseModelToGenreMapper)

    @Provides
    fun providesGenreToGenreDatabaseMapper() = GenreToGenreDatabaseMapper()

    @Provides
    fun providesAlbumsToDatabaseMapper(
        genreToGenreDatabaseMapper: GenreToGenreDatabaseMapper
    ) = AlbumsToDatabaseMapper(genreToGenreDatabaseMapper)

    @Provides
    fun providesConnectivityManager(@ApplicationContext context: Context) =
        NetworkConnectivity(context)
}
