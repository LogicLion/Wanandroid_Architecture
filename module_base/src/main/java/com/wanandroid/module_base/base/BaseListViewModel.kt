package com.wanandroid.module_base.base

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_base.entity.NetListResult

/**
 * @author wzh
 * @date 2023/10/11
 */
abstract class BaseListViewModel<T> : BaseViewModel() {


    abstract val listEntity: MutableLiveData<NetListResult<T>>

    /**
     * 下拉刷新的状态
     */
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)


}