package com.wanandroid.module_user.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_base.base.BaseViewModel
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_user.R

/**
 * @author wzh
 * @date 2023/5/25
 */
@Route(path = ModuleMineAPI.ROUTER_MINE_MINE_FRAGMENT)
class MineFragment : BaseFragment<BaseViewModel>() {
    override fun setupLayoutId() = R.layout.user_fragment_mine

    override fun initView() {
    }
}