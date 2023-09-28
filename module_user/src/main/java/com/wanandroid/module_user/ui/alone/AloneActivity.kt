package com.wanandroid.module_user.ui.alone

import android.os.Bundle
import com.wanandroid.module_base.R
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseActivity
import com.wanandroid.module_base.base.DataBindingConfig

/**
 * @author wzh
 * @date 2023/5/25
 */
class AloneActivity : BaseActivity() {
    override fun getDataBindingConfig() = DataBindingConfig(R.layout.common_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, ModuleMineAPI.getMineFragment())
            .commit()
    }
}