package com.wanandroid.module_article.ui.project

import android.os.Bundle
import com.chad.library.adapter.base.BaseQuickAdapter
import com.wanandroid.module_article.entity.ArticleEntity
import com.wanandroid.module_base.base.BaseListFragment
import org.koin.android.ext.android.inject

/**
 * 项目-项目列表
 * @author wzh
 * @date 2023/10/9
 */
class ProjectListFragment : BaseListFragment<ArticleEntity>() {
    override val adapter: BaseQuickAdapter<ArticleEntity, *> by lazy { ProjectListAdapter() }
    override val viewModel: ProjectListViewModel by inject()

    override val startPageIndex = 1
    private var tabId: Int = -1

    override fun initListFragment() {
        tabId = arguments?.getInt("tabId") ?: -1
    }

    override fun updateList(pageIndex: Int) {
        viewModel.requestList(pageIndex, tabId)
    }


    companion object {
        fun newInstance(tabId: Int): ProjectListFragment {
            val args = Bundle()
            args.putInt("tabId", tabId)
            val fragment = ProjectListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}