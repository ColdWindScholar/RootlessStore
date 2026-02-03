package com.baidaidai.rootless_store.data.market.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.*
import io.ktor.client.request.accept
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import javax.inject.Inject
import io.ktor.http.path

class PluginMarketAPI @Inject constructor(
    private val ktorClient: HttpClient
){
    private val client = ktorClient

    suspend fun getPlugins(
        pageNumber: Int
    ): HttpResponse {
        return client.request("http://192.168.3.10:3000"){
            method = HttpMethod.Get
            accept(ContentType.Application.Json)
            url{
                path("/api/v1/getAllPlugins")
                parameters.append("page", value = pageNumber.toString())
            }
        }
    }
}