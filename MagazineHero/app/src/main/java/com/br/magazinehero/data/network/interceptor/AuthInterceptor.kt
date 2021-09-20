package com.br.magazinehero.data.network.interceptor

import com.br.magazinehero.util.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class AuthInterceptor(): Interceptor {

    // Vou deixar a URL e a API KEY exposta apenas para facilitar para o time de avaliação.

    companion object {
        const val API_KEY = "4f6745c25f517fa2dc99357660c38d9e"
        const val PRIVATE_KEY = "c1ee99905cc53850d1e9f6e8b88963ecf56fbb18"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val ts = "1"
        val hash = "$ts${PRIVATE_KEY}${API_KEY}".md5()
        val url = req.url.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("hash", hash)
            .build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}