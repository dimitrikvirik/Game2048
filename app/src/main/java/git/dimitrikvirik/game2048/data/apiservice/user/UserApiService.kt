package git.dimitrikvirik.game2048.data.apiservice.user

import git.dimitrikvirik.game2048.domain.model.user.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApiService {
    @GET("api")
    suspend fun getRandomUser(
    ): Response<UserResponse>
}