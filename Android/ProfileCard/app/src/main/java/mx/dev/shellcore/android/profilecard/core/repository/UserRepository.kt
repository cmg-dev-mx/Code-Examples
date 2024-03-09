package mx.dev.shellcore.android.profilecard.core.repository

import mx.dev.shellcore.android.profilecard.core.model.UserProfile

interface UserRepository {
    suspend fun getUsers(): List<UserProfile>
    suspend fun getUserById(id: Int): UserProfile
}