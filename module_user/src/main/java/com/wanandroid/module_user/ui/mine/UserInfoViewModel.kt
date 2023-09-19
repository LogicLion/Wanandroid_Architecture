package com.wanandroid.module_user.ui.mine

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_base.base.BaseViewModel
import com.wanandroid.module_base.global.UserInfoData
import com.wanandroid.module_user.net.UserRepository

/**
 * @author wzh
 * @date 2023/7/26
 */
class UserInfoViewModel(private val repository: UserRepository) : BaseViewModel() {
    val avatarUrl = MutableLiveData<String?>()
    val nickName = MutableLiveData("未登陆")

    fun logout() = launchRequest(block = {
        repository.logout()
    }, onComplete = {
        UserInfoData.value = null
    })
}