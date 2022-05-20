package git.dimitrikvirik.game2048.domain.repository.user

import git.dimitrikvirik.game2048.domain.user.response.UserResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getRandomUser(): Response<UserResponse>
}