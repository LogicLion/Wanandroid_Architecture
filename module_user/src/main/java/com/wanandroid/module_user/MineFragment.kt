package com.wanandroid.module_user

import com.alibaba.android.arouter.facade.annotation.Route
import com.doreamon.treasure.base.BaseFragment
import com.doreamon.treasure.base.BaseViewModel
import com.wanandroid.module_base.arouter.api.ModuleMineAPI

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