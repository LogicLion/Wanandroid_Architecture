package com.wanandroid.module_base.global

import androidx.lifecycle.MutableLiveData
import com.doreamon.treasure.utils.decodeString
import com.doreamon.treasure.utils.encode
import com.google.gson.Gson
import com.wanandroid.module_base.constant.DATA_CACHE_KEY_COOKIES
import com.wanandroid.module_base.constant.DATA_CACHE_KEY_USER_INFO
import com.wanandroid.module_base.entity.UserInfoEntity

/**
 * 用户信息
 *
 * - 创建时间：2019/10/14
 *
 * @author 王杰
 */
object UserInfoData : MutableLiveData<UserInfoEntity?>() {

    private var firstLoad = true

    override fun onActive() {
        if (!firstLoad) {
            return
        }
        value =
            Gson().fromJson(DATA_CACHE_KEY_USER_INFO.decodeString(""), UserInfoEntity::class.java)
        firstLoad = false
    }

    override fun setValue(value: UserInfoEntity?) {
        super.setValue(value)
        DATA_CACHE_KEY_USER_INFO.encode(Gson().toJson(value))
        if (null == value) {
            // 退出登录，清空 cookie
            DATA_CACHE_KEY_COOKIES.encode("")
        }
    }
}