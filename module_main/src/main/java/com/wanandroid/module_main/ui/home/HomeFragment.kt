package com.wanandroid.module_main.ui.home

import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_main.R
import com.wanandroid.module_main.databinding.MainFragmentHomeBinding

/**
 * @author wzh
 * @date 2023/5/24
 */
class HomeFragment : BaseFragment<HomeViewModel>() {


    override fun setupLayoutId() = R.layout.main_fragment_home

    override fun initView() {
        val binding = getViewBinding<MainFragmentHomeBinding>()

    }
}