package com.wanandroid.module_article.ui.project

import com.wanandroid.module_article.BR
import com.wanandroid.module_article.R
import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_base.base.DataBindingConfig
import org.koin.android.ext.android.inject

/**
 * 项目-项目列表
 * @author wzh
 * @date 2023/10/9
 */
class ProjectListFragment : BaseFragment() {
    private val viewModel: ProjectListViewModel by inject()
    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.article_fragment_project_list, BR.viewModel, viewModel)

    override fun initView() {

    }
}