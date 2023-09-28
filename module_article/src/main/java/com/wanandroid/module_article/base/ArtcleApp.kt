package com.wanandroid.module_article.base

import android.app.Application
import android.content.Context
import com.doreamon.treasure.base.ApplicationDelegate
import com.wanandroid.module_article.di.netService
import com.wanandroid.module_article.di.repositoryModule
import com.wanandroid.module_article.di.viewModelModule
import org.koin.mp.KoinPlatform

/**
 * @author wzh
 * @date 2023/7/25
 */
class ArticleApp : ApplicationDelegate {
    override fun attachBaseContext(application: Application, context: Context) {
    }

    override fun onCreate(application: Application) {
        KoinPlatform.getKoin().loadModules(listOf(netService, repositoryModule, viewModelModule))
    }
}