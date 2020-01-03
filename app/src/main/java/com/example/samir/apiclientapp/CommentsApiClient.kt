package com.example.samir.apiclientapp

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CommentsApiClient {

    // Get list of articles as Observable
    @GET("posts/{id}/comments")
    fun getComments(): Observable<List<Comment>>

    // Get one article by it's id
    @GET("posts/{id}/comments")
    fun getComment(@Path("id") id: Int): Observable<List<Comment>>

    // Add new article
    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("posts")
    fun addComment(@Body comment: Comment): Observable<Comment>

    companion object {

        fun create(): CommentsApiClient {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .build()

            return retrofit.create(CommentsApiClient::class.java)

        }
    }

}
