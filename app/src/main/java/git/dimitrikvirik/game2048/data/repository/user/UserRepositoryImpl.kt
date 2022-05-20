package git.dimitrikvirik.game2048.data.repository.user

import git.dimitrikvirik.game2048.data.apiservice.user.UserApiService
import git.dimitrikvirik.game2048.domain.repository.user.UserRepository
import git.dimitrikvirik.game2048.domain.user.response.UserResponse
import retrofit2.Response

class UserRepositoryImpl(
    private val userApiService: UserApiService
): UserRepository {
    override suspend fun getRandomUser(): Response<UserResponse>{
        return userApiService.getRandomUser()
    }
}