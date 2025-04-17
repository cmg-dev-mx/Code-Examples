package mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.cmg.android.jobscheduler.repository.source.MyJobScheduler

@Module
@InstallIn(SingletonComponent::class)
abstract class JobSchedulerModule {

    @Binds
    abstract fun bindJobSchedulerSource(
        impl: MyJobSchedulerImpl
    ): MyJobScheduler
}