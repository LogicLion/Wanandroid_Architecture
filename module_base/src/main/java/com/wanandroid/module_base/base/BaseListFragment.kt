package com.wanandroid.module_base.base

import com.chad.library.adapter.base.BaseQuickAdapter
import com.wanandroid.module_base.BR
import com.wanandroid.module_base.R
import com.wanandroid.module_base.databinding.FragmentBaseListBinding

/**
 * 封装了通用的下拉刷新和加载更多的fragment
 * @author wzh
 * @date 2023/10/11
 */
abstract class BaseListFragment<T> : BaseFragment() {

    abstract val adapter: BaseQuickAdapter<T, *>
    abstract val viewModel: BaseListViewModel<T>

    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.fragment_base_list, BR.viewModel, viewModel)

    override fun initView() {

        val binding = getViewBinding<FragmentBaseListBinding>()

        binding.rvSquareArticle.adapter = adapter
        viewModel.isRefreshing.value = true
        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            if (it) viewModel.requestList()
        }

        adapter.loadMoreModule.setOnLoadMoreListener {
            viewModel.requestList()
        }

        viewModel.listEntity.observe(viewLifecycleOwner) {
            viewModel.isRefreshing.value = false
            adapter.setList(it)
        }
    }


}