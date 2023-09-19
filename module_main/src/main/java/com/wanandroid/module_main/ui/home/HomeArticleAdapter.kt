package com.wanandroid.module_main.ui.home

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.module_main.R
import com.wanandroid.module_main.entity.ArticleEntity

/**
 * @author wzh
 * @date 2023/6/13
 */
class HomeArticleAdapter :
    BaseQuickAdapter<ArticleEntity, BaseViewHolder>(R.layout.main_item_home_article),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: ArticleEntity) {
        holder.setText(R.id.tv_article_title, item.title)
        holder.setText(R.id.tv_article_date, item.niceDate)
        holder.setText(
            R.id.tv_article_author,
            if (item.author.isNullOrEmpty()) item.shareUser else item.author
        )

        holder.getView<TextView>(R.id.tv_article_top).apply {
            visibility = if (item.isTop) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        holder.getView<TextView>(R.id.tv_article_fresh).apply {
            visibility = if (item.isNew) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        holder.getView<TextView>(R.id.tv_article_tag).apply {
            if (!item.tags.isNullOrEmpty()) {
                text = item.tags[0].name
                visibility = View.VISIBLE
            } else {
                visibility = View.GONE
            }
        }



        holder.getView<TextView>(R.id.tv_article_chapterName).apply {
            val chapterName = when {
                !item.superChapterName.isNullOrEmpty() and !item.chapterName.isNullOrEmpty() -> {
                    "${item.superChapterName} / ${item.chapterName}"
                }
                !item.superChapterName.isNullOrEmpty() -> {
                    item.superChapterName
                }
                !item.chapterName.isNullOrEmpty() -> {
                    item.chapterName
                }
                else -> ""
            }
            text = chapterName

        }
    }
}