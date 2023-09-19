package com.wanandroid.module_user.ui.login

import androidx.lifecycle.MutableLiveData
import com.doreamon.treasure.ext.toast
import com.wanandroid.module_base.base.BaseViewModel
import com.wanandroid.module_base.global.UserInfoData
import com.wanandroid.module_user.net.UserRepository

/**
 * @author wzh
 * @date 2023/7/5
 */
class LoginViewModel(private val repository: UserRepository) : BaseViewModel() {


    val loginAccount = MutableLiveData("甘雨小可爱")
    val loginPassWord = MutableLiveData("123456")


    fun login() = launchRequest {
        val account = loginAccount.value
        val password = loginPassWord.value
        if (account.isNullOrBlank()) {
            "请输入账号".toast()
            return@launchRequest
        } else if (password.isNullOrBlank()) {
            "请输入密码".toast()
            return@launchRequest
        }
        val userInfoEntity = repository.login(account.trim(), password.trim())
        UserInfoData.value = userInfoEntity
        "登录成功".toast()
    }
}