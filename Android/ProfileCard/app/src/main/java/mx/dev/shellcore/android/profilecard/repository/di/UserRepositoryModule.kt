package mx.dev.shellcore.android.profilecard.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.android.profilecard.core.repository.UserRepository
import mx.dev.shellcore.android.profilecard.repository.UserRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}