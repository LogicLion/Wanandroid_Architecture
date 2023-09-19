package com.wanandroid.module_base.net

import android.util.Log
import com.doreamon.treasure.entity.CookieEntity
import com.doreamon.treasure.utils.AppManager
import com.doreamon.treasure.utils.decodeString
import com.doreamon.treasure.utils.encode
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.wanandroid.module_base.constant.DATA_CACHE_KEY_COOKIES
import com.wanandroid.module_base.localcache.IpManager
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Retrofit管理类
 *
 * @author LTP  2022/3/21
 */
object RetrofitManager {
    /** 请求超时时间 */
    private const val TIME_OUT_SECONDS = 10L


    /** 请求根地址 */
    private val BASE_URL = IpManager.getDefaultIP()

    /** OkHttpClient相关配置 */
    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            // 请求过滤器
            .addNetworkInterceptor(LoggingInterceptor())
            //设置缓存配置,缓存最大10M,设置了缓存之后可缓存请求的数据到data/data/包名/cache/net_cache目录中
            .cache(Cache(File(AppManager.getApplication().cacheDir, "net_cache"), 10 * 1024 * 1024))
            //添加缓存拦截器 可传入缓存天数
            .cookieJar(object : CookieJar {
                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    val cookieEntity = try {
                        Gson().fromJson(
                            DATA_CACHE_KEY_COOKIES.decodeString(""),
                            CookieEntity::class.java
                        )
                    } catch (e: Exception) {
                        Logger.t("NET").e(e, "loadCookie")
                        null
                    }
                    return cookieEntity?.cookies.orEmpty()
                }

                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                    if (cookies.size > 1) {
                        val ls = arrayListOf<Cookie>()
                        ls.addAll(cookies)
                        val cookieEntity = CookieEntity(ls)
                        Gson().toJson(cookieEntity)
                        DATA_CACHE_KEY_COOKIES.encode(Gson().toJson(cookieEntity))
                    }
                }
            })
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()

    /**
     * Retrofit相关配置
     */
    fun <T> getService(serviceClass: Class<T>, baseUrl: String? = null): T {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl ?: BASE_URL)
            .build()
            .create(serviceClass)
    }
}