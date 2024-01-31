package com.wanandroid.module_base.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * @author wzh
 * @date 2023/6/16
 */


object SwipeRefreshLayoutBindingAdapter {

    /**
     * 简单说明下，首先通过bind_swipeRefreshLayout_refreshing属性，将数据viewModel.refreshing绑定到view上，这样数据变化，view也会跟着变化。
     * 然后view变化的时候，通过InverseBindingAdapter注释，会调用bind_refreshingChanged事件，
     * 而bind_refreshingChanged事件告诉了我们view什么时候会进行数据的修改，在这个案例中也就是swipeRefreshLayout下滑的时候会导致数据进行改变，
     * 于是数据对象会从isSwipeRefreshLayoutRefreshing方法获取到最新的数值，也就是从view更新过来的数据。
     *
     * 这里要注意的一个点是，双向绑定要考虑到死循环问题，当View被改变，数据对象对应发生更新，
     * 同时，这个更新又回通知View层去刷新UI，然后view被改变又会导致数据对象更新，无限循环下去了。所以防止死循环的做法就是判断view的数据状态，
     * 当发生改变的时候才去更新view。
     */
    /**
     * 方法1，数据绑定到view
     */
    @JvmStatic
    @BindingAdapter("bind_swipeRefreshLayout_refreshing")
    fun setSwipeRefreshLayoutRefreshing(
        swipeRefreshLayout: SwipeRefreshLayout,
        newValue: Boolean
    ) {
        if (swipeRefreshLayout.isRefreshing != newValue)   // 只有新老状态不同才更新UI
            swipeRefreshLayout.isRefreshing = newValue
    }

    /**
     * 方法2，view改变会通知bind_swipeRefreshLayout_refreshingAttrChanged，并且从该方法获取view的数据
     */
    @JvmStatic
    @InverseBindingAdapter(
        attribute = "bind_swipeRefreshLayout_refreshing",
        event = "bind_swipeRefreshLayout_refreshingAttrChanged"   // 2 【注意！】
    )
    fun isSwipeRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout): Boolean =
        swipeRefreshLayout.isRefreshing


    /**
     * 方法3，view如何改变来影响数据内容
     */
    @JvmStatic
    @BindingAdapter(
        "bind_swipeRefreshLayout_refreshingAttrChanged",
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


}
