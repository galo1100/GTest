package com.galodb.data.network

import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

class GTestClientTest {

    private lateinit var api: GTestApi
    private lateinit var mockServer: MockWebServer

    @Before
    @Throws(IOException::class)
    fun createService() {
        mockServer = MockWebServer()

        val gson = GsonBuilder().create()

        api = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create<GTestApi>(GTestApi::class.java)
    }

    @After
    @Throws(IOException::class)
    fun stopService() {
        mockServer.shutdown()
    }

    @Test
    fun `success get popular tv shows`() {
        enqueueResponse("get_popular_tv_shows.json")
        api.getPopularTvShows("correctApiKey", 1)
            .test()
            .assertValue { it.results.size == 20 }
            .assertValue { it.page == 1 }
            .assertValue { it.totalResults == 10000L }
            .assertValue { it.totalPages == 500 }
    }

    @Test
    fun `error get popular tv shows invalid token`() {
        mockServer.enqueue(MockResponse().setBody("{status_code:7}").setResponseCode(401))
        api.getPopularTvShows("incorrectApiKey", 1)
            .test()
            .assertError(HttpException::class.java)
    }

    @Test
    fun `error get popular tv shows no resource`() {
        mockServer.enqueue(MockResponse().setBody("{status_code:34}").setResponseCode(404))
        api.getPopularTvShows("correctApiKey", 1)
            .test()
            .assertError(HttpException::class.java)
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String) {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream("api-response/$fileName")

        val source = inputStream?.let { Okio.buffer(Okio.source(inputStream)) }
        mockServer.enqueue(MockResponse().setBody(source?.readString(StandardCharsets.UTF_8)))
    }
}
