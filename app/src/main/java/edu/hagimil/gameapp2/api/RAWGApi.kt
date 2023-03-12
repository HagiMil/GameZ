package edu.hagimil.gameapp2.api

import edu.hagimil.gameapp2.models.GameItemDetails
import edu.hagimil.gameapp2.models.GameResponse
import edu.hagimil.gameapp2.utils.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RAWGApi {

    //go to main list and sort by relevance
    @GET("api/games/lists/main")
    suspend fun getPopularGames(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 40,
        @Query("ordering") order: String = "-relevance",
    ): GameResponse

    //go to main list and sort by platform chosen
    @GET("api/games/lists/main")
    suspend fun getPlatformGames(
        @Query("platforms") platform: String,
        @Query("page_size") pageSize: Int = 40,
        @Query("ordering") order: String = "-relevance",
    ): GameResponse

    //go to game page using id provided
    @GET("api/games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Long,
    ): GameItemDetails

    //go to game page using string provided
    @GET("api/games/{slug}")
    suspend fun searchGame(
        @Path("slug") slug: String
    ): GameItemDetails


    //retrofit builder
    companion object {
        fun create(): RAWGApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                //add api key
                .addInterceptor(TokenInterceptor())
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.rawg.io/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(RAWGApi::class.java)
        }
    }
}