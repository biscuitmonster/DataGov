package com.jacob.datagov.api

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response


class ApiClient{

    private fun okHttpClient(context: Context):OkHttpClient {

        return OkHttpClient.Builder().connectTimeout(10 * 60, TimeUnit.SECONDS).writeTimeout(10 * 60, TimeUnit.SECONDS)
            .readTimeout(10 * 60, TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectionInterceptor(context)).build()
    }

    fun getRetrofit(context: Context): Retrofit {
        if (retrofit != null) {
            return retrofit as Retrofit
        }

        retrofit = Retrofit.Builder()
            .baseUrl("https://data.gov.sg")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient(context))
            .build()

        return retrofit as Retrofit
    }



    companion object {
        private val instance by lazy (LazyThreadSafetyMode.NONE){ApiClient() }
        var retrofit: Retrofit? = null

        fun create(context:Context): ApiEndPoint {
            return instance.getRetrofit(context).create(ApiEndPoint::class.java)
        }

    }


    inner class NoConnectivityException : IOException() {

        // You can send any message whatever you want from here.
        override val message: String
            get() = "No Internet Connection"
    }

    inner class NetworkConnectionInterceptor(private val mContext: Context) : Interceptor {

        val isConnected: Boolean
            get() {
                val connectivityManager = mContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = connectivityManager.activeNetworkInfo
                return netInfo != null && netInfo.isConnected
            }

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            if (!isConnected) {
                throw NoConnectivityException()
            }

            val builder = chain.request().newBuilder()
            builder.addHeader("Content-Type", "application/json")
            return chain.proceed(builder.build())
        }

    }
}