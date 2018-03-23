package com.example.dmitry.fakeapitest.network

import com.example.dmitry.fakeapitest.models.Comment
import com.example.dmitry.fakeapitest.models.Post
import com.example.dmitry.fakeapitest.models.User
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FakeApiService {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @GET("users")
    fun getUser(@Query("id") id: Int): Observable<List<User>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Observable<List<Comment>>

    companion object Factory {
        fun create(): FakeApiService {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .client(client)
                    .build()

            return retrofit.create(FakeApiService::class.java)
        }
    }
}