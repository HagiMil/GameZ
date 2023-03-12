package edu.hagimil.gameapp2.utils

import edu.hagimil.gameapp2.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

//interceptor for retrofit
//adds api key
class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url.newBuilder()
            .addQueryParameter("key",BuildConfig.RAWG_API_KEY)
            .build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}