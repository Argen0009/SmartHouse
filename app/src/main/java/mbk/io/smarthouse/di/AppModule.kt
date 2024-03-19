package mbk.io.smarthouse.di

import android.content.Context
import androidx.room.Room
import mbk.io.smarthouse.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mbk.io.myhome.api.ApiService
import mbk.io.smarthouse.data.RMRepository
import mbk.io.smarthouse.domain.base.BaseRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://cars.cprogroup.ru/api/rubetek/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginInterseptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideAppService(
        retrofit: Retrofit
    ): AppModule {
        return retrofit.create(AppModule::class.java)
    }

    @Singleton
    @Provides
    fun provideCameraRepository(api: ApiService,db: AppDatabase): BaseRepository {
        return RMRepository(api,db)
    }

    @Singleton
    @Provides
    fun provideDoorRepository(api: ApiService,db: AppDatabase): BaseRepository {
        return RMRepository(api,db)
    }
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database-name"
        ).build()
    }
}