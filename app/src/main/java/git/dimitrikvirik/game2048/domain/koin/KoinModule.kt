package git.dimitrikvirik.game2048.domain.koin

import git.dimitrikvirik.game2048.data.apiservice.user.UserApiService
import git.dimitrikvirik.game2048.data.repository.user.UserRepositoryImpl
import git.dimitrikvirik.game2048.domain.repository.user.UserRepository
import git.dimitrikvirik.game2048.presentation.vm.ChooseNickNameFragmentVM
import git.dimitrikvirik.game2048.presentation.vm.GameFragmentVM
import git.dimitrikvirik.game2048.presentation.vm.MainMenuFragmentVM
import git.dimitrikvirik.game2048.presentation.vm.ScoreBoardFragmentVM
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val modules = module{
    single {provideOkHttpClient()}
    single {provideRetrofit(get())}
    single {get<Retrofit>().create(UserApiService::class.java)}
    single<UserRepository> {UserRepositoryImpl(get())}
    viewModel {ChooseNickNameFragmentVM(get())}
    viewModel {MainMenuFragmentVM()}
    viewModel {ScoreBoardFragmentVM()}
    viewModel {GameFragmentVM()}
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://randomuser.me/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}