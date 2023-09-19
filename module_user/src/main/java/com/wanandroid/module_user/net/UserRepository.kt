package com.wanandroid.module_user.net

import com.wanandroid.module_base.ext.netRequest
import com.wanandroid.module_base.ext.netRequestIgnoreData

/**
 * @author wzh
 * @date 2023/7/25
 */
class UserRepository(private val netService: NetService) {

    suspend fun login(userName: String, password: String) = netRequest {
        netService.login(userName, password)
    }


    suspend fun logout() = netRequestIgnoreData {
        netService.logout()
    }

}