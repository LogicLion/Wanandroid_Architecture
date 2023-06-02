package com.wanandroid.module_main.net

import com.wanandroid.module_main.ext.netRequest

/**
 * @author wzh
 * @date 2023/5/30
 */
class MainRepository (private val netService: NetService){

    suspend fun getHomepageBannerList() = netRequest {
        netService.getHomepageBannerList()
    }
}