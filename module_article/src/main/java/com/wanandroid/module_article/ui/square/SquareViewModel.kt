package com.wanandroid.module_article.ui.square

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
    override suspend fun requestListFromNet(pageIndex: Int): NetListResult<ArticleEntity> {
        return repository.getSquareArticleList(pageIndex)
    }

}