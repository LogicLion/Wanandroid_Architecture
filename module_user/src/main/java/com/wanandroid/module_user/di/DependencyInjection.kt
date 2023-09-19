package com.wanandroid.module_user.di

import com.wanandroid.module_base.net.RetrofitManager
import com.wanandroid.module_user.net.NetService
import com.wanandroid.module_user.net.UserRepository
import com.wanandroid.module_user.ui.login.LoginViewModel
import com.wanandroid.module_user.ui.mine.MineViewModel
import com.wanandroid.module_user.ui.mine.UserInfoViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author wzh
 * @date 2023/7/25
 */
val netService: Module = module {
    single {
        RetrofitManager.getService(NetService::class.java)
    }
}

/**
 * 数据仓库
 */
val repositoryModule: Module = module {
    single { UserRepository(get()) }
}

val viewModelModule: Module = module {
    single { LoginViewModel(get()) }
    single { MineViewModel() }
    single { UserInfoViewModel(get()) }

}