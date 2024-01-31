package com.wanandroid.module_article.ui.square

import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.module_article.entity.ArticleEntity
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseListFragment
import org.koin.android.ext.android.inject

/**
 * 广场fragment
 * @author wzh
 * @date 2023/9/27
 */
@Route(path = ModuleMineAPI.ROUTER_PATH_SQUARE)
class SquareFragment : BaseListFragment<ArticleEntity>() {
    override val adapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }
    override val viewModel: SquareViewModel by inject()
    override fun updateList(pageIndex: Int) {
        viewModel.requestList(pageIndex)
    }

}