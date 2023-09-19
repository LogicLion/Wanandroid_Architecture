package com.wanandroid.module_user.ui.mine

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_base.base.BaseViewModel

/**
 * @author wzh
 * @date 2023/6/12
 */
class MineViewModel : BaseViewModel() {
    val avatarUrl = MutableLiveData<String?>()
    val nickName = MutableLiveData("未登陆")

}