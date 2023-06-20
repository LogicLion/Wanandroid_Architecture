package com.wanandroid.module_main.ui.home

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_base.base.BaseViewModel
import com.wanandroid.module_main.entity.HomeEntity
import com.wanandroid.module_main.net.MainRepository
import kotlinx.coroutines.async

/**
 * @author wzh
 * @date 2023/5/30
 */
class HomeViewModel(private val repository: MainRepository) : BaseViewModel() {


    val homeEntity by lazy { MutableLiveData<HomeEntity>() }

    /**
     * 下拉刷新的状态
     */
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)


    fun requestHomeData() = launchRequest {
        val bannerAwait = async { repository.getHomepageBannerList() }
        val articleListAwait = async { repository.getHomepageArticleList() }
        homeEntity.value = HomeEntity(bannerAwait.await(), articleListAwait.await().datas)
    }


}