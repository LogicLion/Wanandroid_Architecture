package com.wanandroid.module_main.ui.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.module_main.R
import com.wanandroid.module_main.entity.ArticleEntity

/**
 * @author wzh
 * @date 2023/6/13
 */
class HomeArticleAdapter :
    BaseQuickAdapter<ArticleEntity, BaseViewHolder>(R.layout.main_item_home_article) {
    override fun convert(holder: BaseViewHolder, item: ArticleEntity) {
        holder.setText(R.id.tv_article_title, item.title)
    }
}