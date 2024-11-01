package com.wanandroid.module_article.ui.home

import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.module_article.BR
import com.wanandroid.module_article.R
import com.wanandroid.module_article.databinding.ArticleFragmentHomeBinding
import com.wanandroid.module_article.ui.square.ArticleAdapter
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_base.base.DataBindingConfig
import org.koin.android.ext.android.inject

/**
 * @author wzh
 * @date 2023/5/24
 */
@Route(path = ModuleMineAPI.ROUTER_PATH_HOME)
class HomeFragment : BaseFragment() {

    val viewModel: HomeViewModel by inject()

    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.article_fragment_home, BR.viewModel, viewModel)


    private val bannerAdapter: HomePagerAdapter by lazy {
        HomePagerAdapter()
    }

    private val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }

    override fun initView() {
        val binding = getViewBinding<ArticleFragmentHomeBinding>()

//        binding.appbarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
//            if (viewModel.isRefreshing.value == false) {
//                binding.swipeRefreshLayout.isEnabled = verticalOffset >= 0
//            }
//        }

        binding.viewPager.adapter = bannerAdapter
        binding.rvHomeArticle.adapter = articleAdapter

        binding.swipeRefreshLayout.isEnabled = false
        viewModel.isRefreshing.value = true
        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            if (it) viewModel.requestHomeData()
        }

        articleAdapter.loadMoreModule.setOnLoadMoreListener {
            viewModel.requestHomeArticleList()
        }
        articleAdapter.loadMoreModule.isAutoLoadMore = true
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        articleAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false

        // getViewLifeCycleOwner 是 2020 年新增的特性，
        // 主要是为了解决 getView() 的生命长度 比 fragment 短（仅存活于 onCreateView 之后和 onDestroyView 之前），
        // 导致某些时候 fragment 其他成员还活着，但 getView() 为 null 的 生命周期安全问题，
        // 也即，在 fragment 的场景下，请使用 getViewLifeCycleOwner 来作为 liveData 的观察者。
        // Activity 则不用改变。
        viewModel.homeEntity.observe(viewLifecycleOwner) {
            viewModel.isRefreshing.value = false
            bannerAdapter.setList(it)
        }

        viewModel.articleListEntity.observe(viewLifecycleOwner) {
            articleAdapter.setList(it)
        }

    }

}