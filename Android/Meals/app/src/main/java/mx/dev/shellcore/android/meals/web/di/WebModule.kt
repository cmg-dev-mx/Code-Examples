package mx.dev.shellcore.android.meals.web.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.meals.web.client.MealsWebClient
import mx.dev.shellcore.android.meals.web.service.MealsService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class WebModule {

    @Provides
    fun provideMealsService(retrofit: Retrofit): MealsService {
        return retrofit.create(MealsService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return MealsWebClient.client
    }
}