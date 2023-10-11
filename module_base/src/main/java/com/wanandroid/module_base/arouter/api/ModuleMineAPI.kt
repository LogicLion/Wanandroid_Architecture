package com.wanandroid.module_base.arouter.api

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author wzh
 * @date 2023/5/25
 */
object ModuleMineAPI {

    const val ROUTER_MINE_MINE_FRAGMENT = "/mine/MineFragment"

    /**
     * 登录页[LoginActivity]
     */
    const val ROUTER_PATH_LOGIN = "/user/login"

    /**
     * 用户资料页[UserInfoActivity]
     */
    const val ROUTER_PATH_USER_INFO = "/user/userInfo"

    /**
     * 广场
     */
    const val ROUTER_PATH_SQUARE = "/article/square"

    /**
     * 首页
     */
    const val ROUTER_PATH_HOME = "/article/home"

    /**
     * 项目
     */
    const val ROUTER_PATH_PROJECT = "/article/project"

    fun getMineFragment(): Fragment {
        return ARouter.getInstance().build(ROUTER_MINE_MINE_FRAGMENT).navigation() as Fragment
    }

    fun getHomeFragment(): Fragment {
        return ARouter.getInstance().build(ROUTER_PATH_HOME).navigation() as Fragment
    }


    fun getSquareFragment(): Fragment {
        return ARouter.getInstance().build(ROUTER_PATH_SQUARE).navigation() as Fragment
    }

    fun getProjectFragment(): Fragment {
        return ARouter.getInstance().build(ROUTER_PATH_PROJECT).navigation() as Fragment
    }
}