package com.wanandroid.module_article.ui.project

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.wanandroid.module_article.BR
import com.wanandroid.module_article.R
import com.wanandroid.module_article.databinding.ArticleFragmentProjectBinding
import com.wanandroid.module_article.entity.ProjectTreeEntity
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_base.base.DataBindingConfig
import org.koin.android.ext.android.inject

/**
 * 项目
 * @author wzh
 * @date 2023/10/9
 */
@Route(path = ModuleMineAPI.ROUTER_PATH_PROJECT)
class ProjectFragment : BaseFragment() {
    val viewModel: ProjectViewModel by inject()
    private val projectTreeList = mutableListOf<ProjectTreeEntity>()

    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.article_fragment_project, BR.viewModel, viewModel)

    override fun initView() {

        val binding = getViewBinding<ArticleFragmentProjectBinding>()
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        //这里FragmentStateAdapter的传参一定要传fragment，而不是宿主Activity
        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = projectTreeList.size

            override fun createFragment(position: Int): Fragment {
                return ProjectListFragment.newInstance(projectTreeList[position].id)
            }
        }

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = projectTreeList[position].name
        }.attach()


        viewModel.requestProjectTree()
        viewModel.projectTreeEntity.observe(viewLifecycleOwner) {
            projectTreeList.clear()
            projectTreeList.addAll(it)
            adapter.notifyDataSetChanged()
        }

    }
}