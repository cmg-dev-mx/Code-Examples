package mx.dev.cmg.android.jobscheduler.source.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreSourceModule {

    @Binds
    abstract fun bindDataStoreSource(
        impl: DataStoreSourceImpl
    ): DataStoreSource
}