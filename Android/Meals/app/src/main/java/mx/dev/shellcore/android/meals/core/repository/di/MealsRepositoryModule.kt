package mx.dev.shellcore.android.meals.core.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.android.meals.core.repository.MealsRepository
import mx.dev.shellcore.android.meals.repository.MealsRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class MealsRepositoryModule {

    @Binds
    abstract fun bindMealsRepository(impl: MealsRepositoryImpl): MealsRepository
}