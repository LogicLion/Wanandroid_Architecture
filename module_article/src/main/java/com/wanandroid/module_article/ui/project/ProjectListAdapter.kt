package com.wanandroid.module_article.ui.project

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.doreamon.treasure.utils.ImageUtil
import com.wanandroid.module_article.R
import com.wanandroid.module_article.entity.ArticleEntity

/**
 * @author wzh
 * @date 2023/10/13
 */
class ProjectListAdapter :
    BaseQuickAdapter<ArticleEntity, BaseViewHolder>(R.layout.article_item_project_list),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: ArticleEntity) {

        val authorStr = if (item.author.isNullOrEmpty()) item.shareUser else item.author
        holder.setText(R.id.tv_project_title, Html.fromHtml(item.title))
            .setText(R.id.tv_project_content, Html.fromHtml(item.desc))
            .setText(R.id.tv_project_date, item.niceDate)
            .setText(R.id.tv_project_author, authorStr)
            .setImageResource(
                R.id.iv_project_like,
                if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not
            )
        ImageUtil.loadUrl(holder.getView(R.id.iv_project), item.envelopePic)
    }
}