package com.example.securingsam.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkRequest
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File


interface ApiService {


    @GET("everything")
    fun getEverything(@Query("apiKey")key: String = api_key, @Query("q")q:String="android"): Single<EverythingResponse>

    @GET("top-headlines")
    fun getTopHeadlines(@Query("apiKey")key: String = api_key, @Query("country")country:String = "us"):Single<TopHeadlinesResponse>



    companion object{
        private var api_key = "887d50e6153c4afd8f9d62a8fd9746b3"
        private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        private var interceptor = HttpLoggingInterceptor()


        fun initApiService(context: Context): ApiService {


            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(
                interceptor
            )

            httpClient.addInterceptor {

                val original: Request = it.request()
                val originalHttpUrl: HttpUrl = original.url()

                val url: HttpUrl = originalHttpUrl.newBuilder()
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val request: Request = requestBuilder.build()
                it.proceed(request)
            }


            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?


            val builder = NetworkRequest.Builder()

            connectivityManager!!.registerNetworkCallback(
                builder.build(),
                object : NetworkCallback() {
                    /**
                     * @param network
                     */
                    override fun onAvailable(network: Network) {

                        httpClient.addInterceptor{

                            var original:Response = it.proceed(it.request())
                            var maxAge = 60
                            original.newBuilder().header("Cache-Control", "public, max-age=" + maxAge).build()


                            return@addInterceptor original
                        }

                    }

                    /**
                     * @param network
                     */
                    override fun onLost(network: Network) {
                        httpClient.addInterceptor{
                            val maxStale = 60 * 60 * 24 * 28
                            var original:Response = it.proceed(it.request())
                            original.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                                .build()
                        }





                    }
                }
            )




            val httpCacheDirectory = File(context.getCacheDir(), "responses")
            val cacheSize = 10 * 1024 * 1024

            val cache = Cache(httpCacheDirectory, cacheSize.toLong())

            httpClient.cache(cache)

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://newsapi.org/v2/")
                .build()

            return retrofit.create(ApiService::class.java)
        }


    }
}