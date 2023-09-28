package com.wanandroid.module_main.ui.home

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_base.base.BaseViewModel
import com.wanandroid.module_base.entity.ArticleEntity
import com.wanandroid.module_main.entity.HomeBannerEntity
import com.wanandroid.module_main.net.MainRepository
import kotlinx.coroutines.async

/**
 * @author wzh
 * @date 2023/5/30
 */
class HomeViewModel(private val repository: MainRepository) : BaseViewModel() {


    val homeEntity by lazy { MutableLiveData<List<HomeBannerEntity>>() }
    val articleListEntity by lazy { MutableLiveData<List<ArticleEntity>>() }

    /**
     * 下拉刷新的状态
     */
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)

    private var pageIndex = 0


    /**
     * 获取banner数据+文章列表第一页的数据
     */
    fun requestHomeData() = launchRequest {
        val banner = async { repository.getHomepageBannerList() }
        val articleList = async { repository.getHomepageArticleList(0) }

        if (banner.await().isNotEmpty() && articleList.await().datas != null) {
            homeEntity.value = banner.await()
            articleListEntity.value = articleList.await().datas
            pageIndex = 0
        }
    }

    fun requestHomeArticleList() = launchRequest {
        val listResult = repository.getHomepageArticleList(pageIndex + 1)
        pageIndex = listResult.curPage
        val newArticleList = listResult.datas
        val originValue = articleListEntity.value?.toMutableList()
        newArticleList?.let {
            originValue?.addAll(it)
            articleListEntity.value = originValue
        }

    }


}