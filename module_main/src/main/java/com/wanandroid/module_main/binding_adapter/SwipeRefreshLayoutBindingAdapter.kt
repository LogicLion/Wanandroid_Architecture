package com.wanandroid.module_main.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * @author wzh
 * @date 2023/6/16
 */


object SwipeRefreshLayoutBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:bind_swipeRefreshLayout_refreshing")
    fun setSwipeRefreshLayoutRefreshing(
        swipeRefreshLayout: SwipeRefreshLayout,
        newValue: Boolean
    ) {
        if (swipeRefreshLayout.isRefreshing != newValue)   // 只有新老状态不同才更新UI
            swipeRefreshLayout.isRefreshing = newValue
    }

    /**
     * 这个是触发双向绑定的条件，由InverseBindingAdapter的event
     */
    @JvmStatic
    @BindingAdapter(
        "app:bind_swipeRefreshLayout_refreshingAttrChanged",
        requireAll = false
    )
    fun setOnRefreshListener(
        swipeRefreshLayout: SwipeRefreshLayout,
        bindingListener: InverseBindingListener?
    ) {
        if (bindingListener != null)
            swipeRefreshLayout.setOnRefreshListener {
                bindingListener.onChange()   // 1
            }
    }

    @JvmStatic
    @InverseBindingAdapter(
        attribute = "app:bind_swipeRefreshLayout_refreshing",
        event = "app:bind_swipeRefreshLayout_refreshingAttrChanged"   // 2 【注意！】
    )
    fun isSwipeRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout): Boolean =
        swipeRefreshLayout.isRefreshing
}
