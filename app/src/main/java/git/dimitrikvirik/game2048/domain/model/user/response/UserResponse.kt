package git.dimitrikvirik.game2048.domain.user.response

data class UserResponse(
    val results: List<Result>
)
data class Result(
    val login: Login,

)
data class Login(
    val username: String,
)
