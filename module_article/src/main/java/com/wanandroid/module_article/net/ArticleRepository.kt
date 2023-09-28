package com.wanandroid.module_article.net

import com.wanandroid.module_base.ext.netRequest

/**
 * @author wzh
 * @date 2023/9/27
 */
class ArticleRepository(private val netService: NetService) {

    suspend fun getSquareArticleList(pageIndex: Int) = netRequest {
        netService.getSquareArticleList(pageIndex)
    }
}