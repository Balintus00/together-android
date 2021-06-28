package hu.bme.aut.android.together.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.network.FakeNetworkManager
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(networkManager: FakeNetworkManager) : NetworkDataSource

}