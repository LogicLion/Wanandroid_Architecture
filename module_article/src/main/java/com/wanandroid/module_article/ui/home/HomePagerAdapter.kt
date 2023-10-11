package com.wanandroid.module_article.ui.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.doreamon.treasure.utils.ImageUtil
import com.wanandroid.module_article.R
import com.wanandroid.module_article.entity.HomeBannerEntity

/**
 * @author wzh
 * @date 2023/6/12
 */
class HomePagerAdapter : BaseQuickAdapter<HomeBannerEntity, BaseViewHolder>(R.layout.article_item_home_banner) {
    override fun convert(holder: BaseViewHolder, item: HomeBannerEntity) {
        ImageUtil.loadUrl(holder.getView(R.id.iv_banner), item.imagePath)
    }
}