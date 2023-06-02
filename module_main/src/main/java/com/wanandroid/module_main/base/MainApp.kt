package com.wanandroid.module_main.base

import android.app.Application
import android.content.Context
import com.doreamon.treasure.base.ApplicationDelegate
import com.wanandroid.module_main.di.netModule
import com.wanandroid.module_main.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author wzh
 * @date 2023/5/30
 */
class MainApp : ApplicationDelegate {
    override fun attachBaseContext(application: Application, context: Context) {
        instance = application

    }

    override fun onCreate(application: Application) {

        //初始化koin
        startKoin {
            androidLogger()
            androidContext(application)
            modules(netModule,repositoryModule)
        }


    }


    companion object {
        @JvmStatic
        lateinit var instance: Application
            private set
    }
}