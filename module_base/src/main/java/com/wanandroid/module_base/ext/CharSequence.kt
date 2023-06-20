package com.wanandroid.module_base.ext

/* ----------------------------------------------------------------------------------------- */
/* |                                      字符序列相关                                      | */
/* ----------------------------------------------------------------------------------------- */

/** 字符序列是否不为 `null` 且不是空格 */
fun CharSequence?.isNotNullAndBlank(): Boolean {
    return !isNullOrBlank()
}

/** 字符序列是否不为 `null` 且不是空串 */
fun CharSequence?.isNotNullAndEmpty(): Boolean {
    return !isNullOrEmpty()
}
