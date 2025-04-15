package mx.dev.cmg.android.jobscheduler.core.usecase.onedaylogin

import kotlinx.coroutines.flow.Flow

interface OneDayWithoutLoginUseCase {
    suspend fun validateOneDayWithoutLoginNotification(): Flow<Boolean>
    suspend fun stopJob(): Flow<Boolean>
}
