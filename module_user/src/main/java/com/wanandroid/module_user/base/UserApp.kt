package com.wanandroid.module_user.base

import android.app.Application
import android.content.Context
import com.doreamon.treasure.base.ApplicationDelegate
import com.wanandroid.module_user.di.netService
import com.wanandroid.module_user.di.repositoryModule
import com.wanandroid.module_user.di.viewModelModule
import org.koin.mp.KoinPlatform.getKoin

/**
 * @author wzh
 * @date 2023/7/25
 */
class UserApp : ApplicationDelegate {
    override fun attachBaseContext(application: Application, context: Context) {
    }

    override fun onCreate(application: Application) {

        getKoin().loadModules(listOf(netService, repositoryModule, viewModelModule))
    }
}