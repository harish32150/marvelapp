package com.harish.marvelapp.network

import com.harish.marvelapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.internal.and
import java.security.MessageDigest

class MarvelAuthInterceptor : Interceptor {
  override fun intercept(chain: Chain) =
    chain.request().newBuilder()
      .let { _builder ->
        val _timestamp = timestamp()
        val _url = chain.request().url.newBuilder()
          .addQueryParameter("ts", _timestamp.toString())
          .addQueryParameter("hash", calculateHash(_timestamp))
          .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
          .build()
        _builder
          .url(_url)
          .build()
      }.let { _request ->
        chain.proceed(_request)
      }

  private fun timestamp() = System.currentTimeMillis()

  private fun calculateHash(timestamp: Long): String {
    val _str = "$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
    val _md = MessageDigest.getInstance("MD5")
    val _array = _md.digest(_str.toByteArray())
    return StringBuilder().apply {
      for (i in _array.indices)
        append(Integer.toHexString((_array[i] and 0xFF) or 0x100).substring(1, 3))
    }.toString()
  }
}