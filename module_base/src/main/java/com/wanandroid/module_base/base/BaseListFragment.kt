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

    private var pageIndex = 0

    private var listData = mutableListOf<T>()

    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.fragment_base_list, BR.viewModel, viewModel)

    override fun initView() {

        val binding = getViewBinding<FragmentBaseListBinding>()
        pageIndex = startPageIndex

        initListFragment()
        binding.rvSquareArticle.adapter = adapter
//        viewModel.isRefreshing.value = true

        binding.refreshLayout.setOnRefreshListener {
            pageIndex = startPageIndex
            updateList(pageIndex)
        }

        //注意：主动把isRefreshing设置为true并不会触发setOnRefreshListener的调用
        binding.refreshLayout.isRefreshing = true
        pageIndex = startPageIndex
        updateList(pageIndex)
//        viewModel.isRefreshing.observe(viewLifecycleOwner) {
//            if (it) {
//                pageIndex = if (beginPageIndexZero) 0 else 1
//                updateList(pageIndex)
//            }
//        }

        adapter.loadMoreModule.setOnLoadMoreListener {
            updateList(pageIndex + 1)
        }

        viewModel.listEntity.observe(viewLifecycleOwner) { listResult ->
//            viewModel.isRefreshing.value = false
            binding.refreshLayout.isRefreshing = false

            val curPage = listResult.curPage
            val pageCount = listResult.pageCount
            val list = listResult.datas

            if (curPage < pageCount + startPageIndex) {
                if (list != null && list.isNotEmpty()) {
                    pageIndex = curPage
                    if (pageIndex == startPageIndex) {
                        //第一页
                        listData.clear()
                        adapter.loadMoreModule.isEnableLoadMore = true
                    }

                    listData.addAll(list.toMutableList())
                    adapter.setList(listData)
                }

                if (curPage == pageCount + startPageIndex - 1) {
                    //最后一页
                    adapter.loadMoreModule.isEnableLoadMore = false
                }
            } else {
                adapter.loadMoreModule.isEnableLoadMore = false
            }

        }
    }


    open fun initListFragment() {

    }


    abstract fun updateList(pageIndex: Int)


    /**
     * 初始页码,可能是0或1
     */
    open val startPageIndex = 0
}