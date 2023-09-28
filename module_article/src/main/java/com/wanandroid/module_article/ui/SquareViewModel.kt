package com.wanandroid.module_article.ui

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_article.net.ArticleRepository
import com.wanandroid.module_base.base.BaseViewModel
import com.wanandroid.module_base.entity.ArticleEntity

/**
 * @author wzh
 * @date 2023/9/27
 */
class SquareViewModel(private val repository: ArticleRepository) : BaseViewModel() {
    private var pageIndex = 0

    val articleListEntity by lazy { MutableLiveData<List<ArticleEntity>>() }

    /**
     * 下拉刷新的状态
     */
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)

    fun requestSquareArticleList() = launchRequest {
        val listResult = repository.getSquareArticleList(pageIndex + 1)
        pageIndex = listResult.curPage
        val newArticleList = listResult.datas
        val originValue = articleListEntity.value?.toMutableList() ?: mutableListOf()
        newArticleList?.let {
            originValue.addAll(it)
            articleListEntity.value = originValue
        }

    }
}