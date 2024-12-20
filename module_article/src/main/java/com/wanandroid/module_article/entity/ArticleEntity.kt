package com.wanandroid.module_article.entity

import androidx.databinding.ObservableBoolean
import com.doreamon.treasure.ext.orFalse
import com.wanandroid.module_base.ext.isNotNullAndBlank
import com.wanandroid.module_base.ext.isNotNullAndEmpty
import java.io.Serializable

/**
 * 文章对象数据实体类
 *
 * @param apkLink APK 下载链接
 * @param author 作者
 * @param chapterId 二级分类 id
 * @param chapterName 二级分类名称
 * @param collect 是否收藏
 * @param courseId ？
 * @param desc 描述
 * @param envelopePic 文章里的图片地址
 * @param fresh 是否是新文章
 * @param id 文章 id
 * @param link 文章跳转链接
 * @param niceDate 显示时间
 * @param origin ？
 * @param originId ？
 * @param prefix ？
 * @param projectLink ？
 * @param publishTime 发布时间
 * @param superChapterId 一级分类 id
 * @param superChapterName 一级分类名称
 * @param tags 标签列表
 * @param title 标题
 * @param type ？
 * @param userId ？
 * @param visible ？
 * @param zan 赞 数量
 * @param top 自设属性 是否置顶
 */
data class ArticleEntity(
    val apkLink: String? = "",
    val author: String? = "",
    val chapterId: String? = "",
    val chapterName: String? = "",
    val collect: Boolean = false,
    val courseId: String? = "",
    val desc: String? = "",
    val envelopePic: String? = "",
    val fresh: String? = "",
    val id: String? = "",
    val link: String? = "",
    val niceDate: String? = "",
    val shareUser: String? = "",
    val origin: String? = "",
    val originId: String? = "",
    val prefix: String? = "",
    val projectLink: String? = "",
    val publishTime: String? = "",
    val superChapterId: String? = "",
    val superChapterName: String? = "",
    val tags: ArrayList<ArticleTagEntity>? = arrayListOf(),
    val title: String? = "",
    val type: String? = "",
    val userId: String? = "",
    val visible: String? = "",
    val zan: String? = "",
    val top: String? = ""
) : Serializable {
    /** 标记 - 是否置顶 */
    val isTop: Boolean
        get() = top == "1"

    /** 标记 - 是否最新 */
    val isNew: Boolean
        get() = fresh?.toBoolean().orFalse()

    /** 标记 - 是否显示标签 */
    val showTags: Boolean
        get() = tags.isNotNullAndEmpty()

    /** 标记 - 是否收藏 */
    @Transient
    val collected: ObservableBoolean = ObservableBoolean(false)

    /** 标记 - 是否显示封面 */
    val showEnvelope: Boolean
        get() = envelopePic.isNotNullAndBlank()
}

/**
 * 文章标签数据实体类
 *
 * @param name 标签名
 * @param url 标签地址
 */
data class ArticleTagEntity(
    val name: String? = "",
    val url: String? = ""
) : Serializable