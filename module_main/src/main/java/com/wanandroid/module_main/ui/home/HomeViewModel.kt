package com.wanandroid.module_main.ui.home

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_base.base.BaseViewModel
import com.wanandroid.module_main.net.MainRepository
import org.koin.core.component.inject

/**
 * @author wzh
 * @date 2023/5/30
 */
class HomeViewModel : BaseViewModel() {

    private val repository: MainRepository by inject()


    val ts = MutableLiveData<String?>()


    init {
        getHomeBanner()
    }


    private fun getHomeBanner() = launchRequest {
        val bannerList = repository.getHomepageBannerList()

        ts.value = bannerList.toString()

    }
}