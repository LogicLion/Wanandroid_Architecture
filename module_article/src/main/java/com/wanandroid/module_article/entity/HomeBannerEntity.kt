package com.wanandroid.module_article.entity

/**
 * @author wzh
 * @date 2023/5/31
 */

/**
 * 首页 Banner 数据实体类
 *
 * @param desc 描述
 * @param id Banner id
 * @param imagePath 图片地址
 * @param isVisible ？
 * @param order ？
 * @param title 标题
 * @param type ？
 * @param url 跳转链接
 */
data class HomeBannerEntity(
    val desc: String? = "",
    val id: String? = "",
    val imagePath: String? = "",
    val isVisible: String? = "",
    val order: String? = "",
    val title: String? = "",
    val type: String? = "",
    val url: String? = ""
) : java.io.Serializable