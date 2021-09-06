package com.harish.marvelapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harish.marvelapp.BuildConfig
import com.harish.marvelapp.network.MarvelAuthInterceptor
import com.harish.marvelapp.network.MarvelService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
  factory<GsonConverterFactory> {
    GsonConverterFactory.create()
  }
  factory<Gson> {
    GsonBuilder().create()
  }
  factory<RxJava3CallAdapterFactory> {
    RxJava3CallAdapterFactory.create()
  }
  factory {
    providesOkhttp()
  }
  single {
    providesRetrofit(get(), get(), get())
  }
  single {
    provideMarvelService(get())
  }
}

private fun providesOkhttp(): OkHttpClient {
  val client = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)

  client.addNetworkInterceptor(MarvelAuthInterceptor())

  if (BuildConfig.DEBUG) {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    client.addInterceptor(logging)
  }
  return client.build()
}

private fun providesRetrofit(
  gsonConverterFactory: GsonConverterFactory,
  rxJava2CallAdapterFactory: RxJava3CallAdapterFactory,
  okHttpClient: OkHttpClient
): Retrofit {
  return Retrofit.Builder()
    .baseUrl(BuildConfig.API_BASE)
    .addConverterFactory(gsonConverterFactory)
    .addCallAdapterFactory(rxJava2CallAdapterFactory)
    .client(okHttpClient)
    .build()
}

private fun provideMarvelService(retrofit: Retrofit): MarvelService =
  retrofit.create(MarvelService::class.java)