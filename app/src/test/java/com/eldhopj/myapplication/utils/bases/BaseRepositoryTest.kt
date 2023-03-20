package com.eldhopj.myapplication.utils.bases

import android.content.Context
import com.eldhopj.myapplication.data.remote.ErrorData
import com.eldhopj.myapplication.data.remote.Output
import com.eldhopj.myapplication.utils.DeserializerAdapter
import com.eldhopj.myapplication.utils.MockResponseFileReader
import com.google.gson.GsonBuilder
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.mockito.Mockito
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

abstract class BaseRepositoryTest<T> where T : Any {

    private val context: Context = Mockito.mock(Context::class.java)

    private lateinit var mockWebServer: MockWebServer
    protected val url: String
        get() = mockWebServer.url("/").toString()
    abstract val api: T

    @Before
    open fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    fun mockHttpResponse(res: String, responseCode: Int) =
        mockWebServer.enqueue(MockResponse().setResponseCode(responseCode).apply {
            this.setBody(MockResponseFileReader(res).content)
        })


    protected fun mockHttpBodyResponse(body: String?, responseCode: Int) {
        return mockWebServer.enqueue(MockResponse().setResponseCode(responseCode).apply {
            if (body != null) {
                this.setBody(body)
            }
        })
    }

    private fun Context.readResource(res: String): String {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(res)
        val source = inputStream.source().buffer()
        return source.readString(Charsets.UTF_8)
    }

    inline fun <reified T> createApi(url: String): T {
        return Retrofit.Builder().baseUrl(url).client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).callTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build()
        )
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        Output::class.java, DeserializerAdapter()
                    ).create()
                )
            ).build().create()
    }

    /**
     * Safe d b call
     *
     * @param T  data type
     * @param dispatcher CoroutineDispatcher
     * @param dbCall roomDb call
     * @receiver
     * @return
     */
    fun <T> safeDBCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        dbCall: suspend () -> T
    ): Flow<Output<T>> = flow {
        emit(Output.Loading(true))
        emit(Output.Success(dbCall.invoke()))
        emit(Output.Loading(false))
    }.catch { e ->
        emit(Output.Loading(false))
        emit(Output.Exception(e))
    }.flowOn(dispatcher)

    /**
     * Safe api call
     *
     * @param T Response data type
     * @param dispatcher CoroutineDispatcher
     * @param apiCall api Call
     * @receiver
     * @return
     */
    fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> Response<T>
    ): Flow<Output<T>> = flow {
        emit(Output.Loading(true))
        val response = apiCall()
        emit(Output.Loading(false))
        if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                emit(Output.Success(data))
            } else {
                val errorCode = response.code()
                val error = response.errorBody()
                if (error != null) {
                    emit(Output.Error(ErrorData(errorCode, error.toString())))
                } else {
                    emit(Output.Error(ErrorData(errorCode)))
                }
            }
        } else {
            emit(Output.Error(ErrorData(response.code(), response.message())))
        }
    }.catch { e ->
        emit(Output.Loading(false))
        emit(Output.Exception(e))
    }.flowOn(dispatcher)
}
