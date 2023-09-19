package com.wanandroid.module_user.ui.login

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseActivity
import com.wanandroid.module_base.base.DataBindingConfig
import com.wanandroid.module_base.global.UserInfoData
import com.wanandroid.module_user.BR
import com.wanandroid.module_user.R
import com.wanandroid.module_user.databinding.UserActivityLoginBinding
import org.koin.android.ext.android.inject

/**
 * @author wzh
 * @date 2023/7/5
 */
@Route(path = ModuleMineAPI.ROUTER_PATH_LOGIN)
class LoginActivity : BaseActivity() {
    private val loginViewModel: LoginViewModel by inject()
    private val binding by lazy { getViewBinding<UserActivityLoginBinding>() }
    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.user_activity_login, BR.viewModel, loginViewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            loginViewModel.login()
        }

        UserInfoData.observe(this) {
            if (it != null) {
                finish()
            }
        }
    }
}