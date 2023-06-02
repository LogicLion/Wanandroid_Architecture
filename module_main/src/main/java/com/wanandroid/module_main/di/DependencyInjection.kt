package com.wanandroid.module_main.di

import com.orhanobut.logger.Logger
import com.wanandroid.module_base.constant.BASE_URL
import com.wanandroid.module_main.BuildConfig
import com.wanandroid.module_main.net.LoggingInterceptor
import com.wanandroid.module_main.net.MainRepository
import com.wanandroid.module_main.net.NetService
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author wzh
 * @date 2023/5/30
 */

@JvmField
val netModule: Module = module {

    single {
        //缓存路径
        val logger = object : InterceptorLogger {
            override fun invoke(msg: String) {
                Logger.t("NET").i(msg)
            }
        }
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
//            .cookieJar(object : CookieJar {
//                override fun loadForRequest(url: HttpUrl): List<Cookie> {
//                    val cookieEntity = try {
//                        DATA_CACHE_KEY_COOKIES.decodeString("").toTypeEntity<CookieEntity>()
//                    } catch (e: Exception) {
//                        Logger.t("NET").e(e, "loadCookie")
//                        null
//                    }
//                    return cookieEntity?.cookies.orEmpty()
//                }
//
//                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
//                    if (cookies.size > 1) {
//                        val ls = arrayListOf<Cookie>()
//                        ls.addAll(cookies)
//                        val cookieEntity = CookieEntity(ls)
//                        DATA_CACHE_KEY_COOKIES.encode(cookieEntity.toJsonString())
//                    }
//                }
//            })
            .addNetworkInterceptor(
                LoggingInterceptor()
            )
            .connectTimeout(5000L, TimeUnit.MILLISECONDS)
            .readTimeout(5000L, TimeUnit.MILLISECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<NetService> {
        get<Retrofit>().create(NetService::class.java)
    }
}

/**
 * 数据仓库
 */
val repositoryModule: Module = module {
    factory { MainRepository(get()) }
}

/** 日志打印接口 */
typealias InterceptorLogger = (String) -> Unit

val DEFAULT_LOGGER: InterceptorLogger by lazy(LazyThreadSafetyMode.NONE) {
    object : InterceptorLogger {
        override fun invoke(msg: String) {

            Platform.get().log(msg)
        }
    }
}