package com.wanandroid.module_base.entity

import java.io.Serializable

/**
 *
 * 网络请求返回LIST数据基本模型
 *
 * @param curPage 当前页码
 * @param offset 已显示数量
 * @param pageCount 总页数
 * @param size 当前页数据数
 * @param total 总数据量
 * @param over 是否结束
 * @param datas 列表数据
 */
data class NetListResult<T>(
    val curPage: String? = "",
    val offset: String? = "",
    val pageCount: String? = "",
    val size: String? = "",
    val total: String? = "",
    val over: String? = "",
    val datas: ArrayList<T>? = arrayListOf()
) : Serializable