package com.wanandroid.module_article.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.module_article.BR
import com.wanandroid.module_article.R
import com.wanandroid.module_article.databinding.ArticleFragmentSquareBinding
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_base.base.DataBindingConfig
import org.koin.android.ext.android.inject

/**
 * 广场fragment
 * @author wzh
 * @date 2023/9/27
 */
@Route(path = ModuleMineAPI.ROUTER_PATH_SQUARE)
class SquareFragment : BaseFragment() {
    private val viewModel: SquareViewModel by inject()

    private val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }

    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.article_fragment_square, BR.viewModel, viewModel)

    override fun initView() {

        val binding = getViewBinding<ArticleFragmentSquareBinding>()

        binding.rvSquareArticle.adapter = articleAdapter
        viewModel.isRefreshing.value = true
        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            if (it) viewModel.requestSquareArticleList()
        }

        articleAdapter.loadMoreModule.setOnLoadMoreListener {
            viewModel.requestSquareArticleList()
        }

        viewModel.articleListEntity.observe(viewLifecycleOwner) {
            viewModel.isRefreshing.value = false
            articleAdapter.setList(it)
        }
    }
}