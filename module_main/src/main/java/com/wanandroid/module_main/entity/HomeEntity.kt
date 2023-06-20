package com.wanandroid.module_main.entity

import java.io.Serializable

/**
 * 首页数据
 * @author wzh
 * @date 2023/6/14
 */
class HomeEntity(val bannerEntity: List<HomeBannerEntity>?, val articleEntity: List<ArticleEntity>?) :
    Serializable