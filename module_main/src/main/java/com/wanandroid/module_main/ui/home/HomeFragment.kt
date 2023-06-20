package com.wanandroid.module_main.ui.home

import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_base.base.DataBindingConfig
import com.wanandroid.module_main.BR
import com.wanandroid.module_main.R
import com.wanandroid.module_main.databinding.MainFragmentHomeBinding
import org.koin.android.ext.android.inject

/**
 * @author wzh
 * @date 2023/5/24
 */
class HomeFragment : BaseFragment() {

    val viewModel: HomeViewModel by inject()
    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.main_fragment_home, BR.viewModel, viewModel)


    private val bannerAdapter: HomePagerAdapter by lazy {
        HomePagerAdapter()
    }

    private val articleAdapter: HomeArticleAdapter by lazy {
        HomeArticleAdapter()
    }

    override fun initView() {
        val binding = getViewBinding<MainFragmentHomeBinding>()

        binding.appbarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (viewModel.isRefreshing.value == false) {
                binding.swipeRefreshLayout.isEnabled = verticalOffset >= 0
            }
        }

        binding.viewPager.adapter = bannerAdapter
        binding.rvHomeArticle.adapter = articleAdapter

        viewModel.isRefreshing.value = true
        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            if (it) viewModel.requestHomeData()
        }

        // getViewLifeCycleOwner 是 2020 年新增的特性，
        // 主要是为了解决 getView() 的生命长度 比 fragment 短（仅存活于 onCreateView 之后和 onDestroyView 之前），
        // 导致某些时候 fragment 其他成员还活着，但 getView() 为 null 的 生命周期安全问题，
        // 也即，在 fragment 的场景下，请使用 getViewLifeCycleOwner 来作为 liveData 的观察者。
        // Activity 则不用改变。
        viewModel.homeEntity.observe(viewLifecycleOwner) {
            viewModel.isRefreshing.value = false
            bannerAdapter.setList(it.bannerEntity)
            articleAdapter.setList(it.articleEntity)
        }

    }

}