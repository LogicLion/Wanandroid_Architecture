package com.wanandroid.module_article.net

import com.wanandroid.module_article.entity.ArticleEntity
import com.wanandroid.module_article.entity.HomeBannerEntity
import com.wanandroid.module_article.entity.ProjectTreeEntity
import com.wanandroid.module_base.entity.NetListResult
import com.wanandroid.module_base.entity.NetResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author wzh
 * @date 2023/9/27
 */
interface NetService {
    /**
     * 首页-banner
     */
    @GET("/banner/json")
    suspend fun getHomepageBannerList(): NetResult<List<HomeBannerEntity>>


    /**
     * 首页-文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomepageArticleList(@Path("page") pageIndex: Int): NetResult<NetListResult<ArticleEntity>>

    /**
     * 广场-文章列表
     */
    @GET("user_article/list/{page}/json")
    suspend fun getSquareArticleList(@Path("page") pageIndex: Int): NetResult<NetListResult<ArticleEntity>>


    /**
     * 项目-项目分类
     */
    @GET("project/tree/json")
    suspend fun getProjectTree(): NetResult<List<ProjectTreeEntity>>



}