package com.wanandroid.module_main.net

import com.wanandroid.module_base.entity.ArticleEntity
import com.wanandroid.module_base.entity.NetListResult
import com.wanandroid.module_base.entity.NetResult
import com.wanandroid.module_main.entity.HomeBannerEntity
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author wzh
 * @date 2023/5/30
 */
interface NetService {

    /**
     * 首页banner
     */
    @GET("/banner/json")
    suspend fun getHomepageBannerList(): NetResult<List<HomeBannerEntity>>


    /**
     * 首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomepageArticleList(@Path("page") pageIndex:Int): NetResult<NetListResult<ArticleEntity>>
}