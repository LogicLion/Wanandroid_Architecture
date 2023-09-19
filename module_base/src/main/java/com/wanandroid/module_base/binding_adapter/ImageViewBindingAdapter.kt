package com.wanandroid.module_base.binding_adapter

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.doreamon.treasure.utils.ImageUtil
import com.wanandroid.module_base.R

/**
 * @author wzh
 * @date 2023/7/25
 */
object ImageViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("bind_imageview_url")
    fun setImageViewUrl(
        imageView: ImageView,
        url: String?
    ) {
        ImageUtil.loadUrl(imageView, url)
    }


    @JvmStatic
    @BindingAdapter("bind_imageview_drawable")
    fun setImageViewDrawable(
        imageView: ImageView,
        @DrawableRes resourceId: Int?
    ) {
        ImageUtil.loadDrawable(imageView, resourceId)
    }


    @JvmStatic
    @BindingAdapter("bind_imageview_avatar")
    fun setImageViewAvatar(
        imageView: ImageView,
        url: String?
    ) {
        if (url.isNullOrBlank()) {
            ImageUtil.loadDrawable(imageView, R.drawable.pic_ganyu)
        } else {
            ImageUtil.loadUrl(imageView, url)
        }
    }
}