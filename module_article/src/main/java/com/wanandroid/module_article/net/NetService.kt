package com.wanandroid.module_article.net

import com.wanandroid.module_base.entity.ArticleEntity
import com.wanandroid.module_base.entity.NetListResult
import com.wanandroid.module_base.entity.NetResult
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author wzh
 * @date 2023/9/27
 */
interface NetService {
    /**
     * 广场-文章列表
     */
    @GET("user_article/list/{page}/json")
    suspend fun getSquareArticleList(@Path("page") pageIndex:Int): NetResult<NetListResult<ArticleEntity>>
}