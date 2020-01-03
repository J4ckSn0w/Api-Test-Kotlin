package com.example.samir.apiclientapp

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserApiClient {

    // Get list of articles as Observable
    @GET("users")
    fun getUsers(): Observable<List<User>>

    // Get one article by it's id
    @GET("users")
    fun getUser(@Path("id") id: Int): Observable<List<User>>

    // Add new article
    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("posts")
    fun addUser(@Body user: User): Observable<User>

    companion object {

        fun create(): UserApiClient {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .build()

            return retrofit.create(UserApiClient::class.java)

        }
    }

}
