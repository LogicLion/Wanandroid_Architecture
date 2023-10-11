package com.wanandroid.module_article.net

import com.wanandroid.module_base.ext.netRequest

/**
 * @author wzh
 * @date 2023/9/27
 */
class ArticleRepository(private val netService: NetService) {

    suspend fun getHomepageBannerList() = netRequest {
        netService.getHomepageBannerList()
    }

    suspend fun getHomepageArticleList(pageIndex: Int) = netRequest {
        netService.getHomepageArticleList(pageIndex)
    }

    suspend fun getSquareArticleList(pageIndex: Int) = netRequest {
        netService.getSquareArticleList(pageIndex)
    }

    suspend fun getProjectTree() = netRequest {
        netService.getProjectTree()
    }
}