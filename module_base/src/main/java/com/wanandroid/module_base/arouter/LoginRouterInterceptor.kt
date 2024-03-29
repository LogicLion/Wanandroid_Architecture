package com.wanandroid.module_base.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter
import com.wanandroid.module_base.arouter.api.ModuleMineAPI.ROUTER_PATH_LOGIN
import com.wanandroid.module_base.arouter.api.ModuleMineAPI.ROUTER_PATH_USER_INFO
import com.wanandroid.module_base.global.UserInfoData

/**
 * 登录状态拦截器
 *
 * - 创建时间：2021/1/19
 *
 * @author 王杰
 */
@Interceptor(priority = 8, name = "登录状态拦截器")
class LoginRouterInterceptor : IInterceptor {

    override fun init(context: Context) {
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        if (needIntercept(postcard.path)) {
            // 需要登录验证
            if (null == UserInfoData.value) {
                // 未登录，转发到登录页
                ARouter.getInstance().build(ROUTER_PATH_LOGIN).navigation()
                callback.onInterrupt(Exception("must login first!"))
                return
            }
        }
        // 处理完成，交还控制
        callback.onContinue(postcard)
    }

    /** 根据 [path] 判断是否需要拦截 */
    private fun needIntercept(path: String): Boolean {
        return path in arrayOf(
            ROUTER_PATH_USER_INFO// 用户资料
        )
    }
}