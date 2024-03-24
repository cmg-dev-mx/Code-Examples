package mx.dev.shellcore.android.notas.db.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.notas.db.source.DataBaseSourceImpl
import mx.dev.shellcore.android.notas.repository.source.DataBaseSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBaseModule {

    @Binds
    abstract fun bindDataBaseSource(impl: DataBaseSourceImpl): DataBaseSource
}