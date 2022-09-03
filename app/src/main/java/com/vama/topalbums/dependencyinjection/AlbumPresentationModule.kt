package com.vama.topalbums.dependencyinjection

import android.content.Context
import com.vama.topalbums.domain.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object AlbumPresentationModule {

    @Provides
    fun providesConnectivityManager(@ApplicationContext context: Context) =
        NetworkConnectivity(context)
}
