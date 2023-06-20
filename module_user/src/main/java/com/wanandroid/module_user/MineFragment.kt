package com.wanandroid.module_user

import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_user.BR
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.DataBindingConfig

/**
 * @author wzh
 * @date 2023/5/25
 */
@Route(path = ModuleMineAPI.ROUTER_MINE_MINE_FRAGMENT)
class MineFragment : BaseFragment() {
    override fun getDataBindingConfig()=
        DataBindingConfig(R.layout.user_fragment_mine,BR.viewModel,MineViewModel())

    override fun initView() {
    }
}