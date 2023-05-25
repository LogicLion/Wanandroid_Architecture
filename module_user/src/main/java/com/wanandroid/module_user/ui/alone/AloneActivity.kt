package com.wanandroid.module_user.ui.alone

import android.os.Bundle
import com.doreamon.treasure.base.BaseActivity
import com.doreamon.treasure.base.BaseViewModel
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_user.R

/**
 * @author wzh
 * @date 2023/5/25
 */
class AloneActivity : BaseActivity<BaseViewModel>() {
    override fun setupLayoutId()= R.layout.common_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, ModuleMineAPI.getMineFragment())
            .commit()
    }
}