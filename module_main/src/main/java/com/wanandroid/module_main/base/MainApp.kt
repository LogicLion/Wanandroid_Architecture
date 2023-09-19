package com.wanandroid.module_main.base

import android.app.Application
import android.content.Context
import com.doreamon.treasure.base.ApplicationDelegate
import com.wanandroid.module_main.di.netService
import com.wanandroid.module_main.di.repositoryModule
import com.wanandroid.module_main.di.viewModelModule
import org.koin.mp.KoinPlatform.getKoin

/**
 * @author wzh
 * @date 2023/5/30
 */
class MainApp : ApplicationDelegate {
    override fun attachBaseContext(application: Application, context: Context) {
        instance = application

    }

    override fun onCreate(application: Application) {

        getKoin().loadModules(listOf(netService, repositoryModule, viewModelModule))
    }


    companion object {
        @JvmStatic
        lateinit var instance: Application
            private set
    }
}