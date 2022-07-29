package com.example.fruits.data.api

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.common.truth.Truth.assertThat

class FruitsApiTest {
    private lateinit var service: FruitsApi
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(FruitsApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(fileName: String) {
        var inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        var source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getFruits_sentRequest_RecivecorrectResponse() {
        runBlocking {
            enqueueMockResponse("fruitsresponse.json")
            var response = service.fruitsImages("Mixed Fruit Pack", page = 1, perPage = 10).body()
            val request = server.takeRequest()
            assertThat(response).isNotNull()
        val results=response?.results
            assertThat(results?.size).isEqualTo(10)

        }
    }
}