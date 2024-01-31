package com.wanandroid.module_article.ui.square

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_article.entity.ArticleEntity
import com.wanandroid.module_article.net.ArticleRepository
import com.wanandroid.module_base.base.BaseListViewModel
import com.wanandroid.module_base.entity.NetListResult

/**
 * @author wzh
 * @date 2023/9/27
 */
class SquareViewModel(private val repository: ArticleRepository) :
    BaseListViewModel<ArticleEntity>() {

    override val listEntity by lazy {
        MutableLiveData<NetListResult<ArticleEntity>>()
    }

    fun requestList(pageIndex: Int) = launchRequest {
        listEntity.value = repository.getSquareArticleList(pageIndex)
    }


}