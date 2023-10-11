package com.wanandroid.module_base.base

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_base.entity.NetListResult

/**
 * @author wzh
 * @date 2023/10/11
 */
abstract class BaseListViewModel<T> : BaseViewModel() {
    private var pageIndex = 0

    val listEntity by lazy { MutableLiveData<List<T>>() }

    /**
     * 下拉刷新的状态
     */
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)

    fun requestList() = launchRequest {
        val listResult = requestListFromNet(pageIndex + 1)
        pageIndex = listResult.curPage
        val newArticleList = listResult.datas
        val originValue = listEntity.value?.toMutableList() ?: mutableListOf()
        newArticleList?.let {
            originValue.addAll(it)
            listEntity.value = originValue
        }
    }

    abstract suspend fun requestListFromNet(pageIndex: Int): NetListResult<T>

}