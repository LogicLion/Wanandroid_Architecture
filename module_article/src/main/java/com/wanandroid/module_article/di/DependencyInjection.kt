package com.wanandroid.module_article.di

import com.wanandroid.module_article.net.ArticleRepository
import com.wanandroid.module_article.net.NetService
import com.wanandroid.module_article.ui.home.HomeFragment
import com.wanandroid.module_article.ui.home.HomeViewModel
import com.wanandroid.module_article.ui.project.ProjectFragment
import com.wanandroid.module_article.ui.project.ProjectListFragment
import com.wanandroid.module_article.ui.project.ProjectListViewModel
import com.wanandroid.module_article.ui.project.ProjectViewModel
import com.wanandroid.module_article.ui.square.SquareFragment
import com.wanandroid.module_article.ui.square.SquareViewModel
import com.wanandroid.module_base.net.RetrofitManager
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author wzh
 * @date 2023/5/30
 */

//@JvmField
//val netModule: Module = module {
//
//    single {
//        //缓存路径
//        val logger = object : InterceptorLogger {
//            override fun invoke(msg: String) {
//                Logger.t("NET").i(msg)
//            }
//        }
//        OkHttpClient.Builder()
//            .retryOnConnectionFailure(true)
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
//            .addNetworkInterceptor(
//                LoggingInterceptor()
//            )
//            .connectTimeout(5000L, TimeUnit.MILLISECONDS)
//            .readTimeout(5000L, TimeUnit.MILLISECONDS)
//            .build()
//    }
//
//    single<Retrofit> {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(get())
//            .build()
//    }
//
//    single<NetService> {
////        get<Retrofit>().create(NetService::class.java)
//        RetrofitManager.getService(NetService::class.java)
//
//    }
//}


///** 日志打印接口 */
//typealias InterceptorLogger = (String) -> Unit
//
//val DEFAULT_LOGGER: InterceptorLogger by lazy(LazyThreadSafetyMode.NONE) {
//    object : InterceptorLogger {
//        override fun invoke(msg: String) {
//
//            Platform.get().log(msg)
//        }
//    }
//}

val netService: Module = module {
    single {
        RetrofitManager.getService(NetService::class.java)
    }
}

/**
 * 数据仓库
 */
val repositoryModule: Module = module {
    single { ArticleRepository(get()) }
}

val viewModelModule: Module = module {

    scope<SquareFragment> {
        scoped {
            SquareViewModel(get())
        }
    }

    scope<HomeFragment> {
        scoped {
            HomeViewModel(get())
        }
    }

    scope<ProjectFragment> {
        scoped {
            ProjectViewModel(get())
        }
    }

    scope<ProjectListFragment> {
        scoped {
            ProjectListViewModel(get())
        }
    }
}