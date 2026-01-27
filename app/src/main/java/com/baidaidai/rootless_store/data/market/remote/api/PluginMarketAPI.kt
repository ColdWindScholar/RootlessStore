package com.baidaidai.rootless_store.data.market.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.*
import io.ktor.client.request.accept
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import javax.inject.Inject
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PluginMarketAPI @Inject constructor(){
    private val client = HttpClient(Android){
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }
    }

    suspend fun getPlugins(
        pageNumber: Int
    ): HttpResponse {
        return client.request("http://192.168.3.10:3000/api/v1/getAllPlugins"){
            method = HttpMethod.Get
            accept(ContentType.Application.Json)
            url{
                parameters.append("page", value = pageNumber.toString())
            }
        }
    }
}