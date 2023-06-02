package com.wanandroid.module_main.net

import com.wanandroid.module_base.entity.NetResult
import com.wanandroid.module_main.entity.HomeBannerEntity
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author wzh
 * @date 2023/5/30
 */
interface NetService {

    /**
     * 首页banner
     */
    @GET("/banner/json")
    suspend fun getHomepageBannerList(): NetResult<HomeBannerEntity>
}