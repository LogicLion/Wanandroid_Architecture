package com.wanandroid.module_main.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.doreamon.treasure.ext.toast
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseActivity
import com.wanandroid.module_base.base.DataBindingConfig
import com.wanandroid.module_main.R
import com.wanandroid.module_main.databinding.MainActivityMainBinding

/**
 * @author wzh
 * @date 2023/5/22
 */
class MainActivity : BaseActivity() {
    private var lastPressBackTime: Long = 0L
    override fun getDataBindingConfig() = DataBindingConfig(R.layout.main_activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = getViewBinding<MainActivityMainBinding>()
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 4

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    1 -> ModuleMineAPI.getSquareFragment()
                    2 -> ModuleMineAPI.getProjectFragment()
                    3 -> ModuleMineAPI.getMineFragment()

                    else -> ModuleMineAPI.getHomeFragment()
                }
            }
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.menu_topic -> binding.viewPager.setCurrentItem(1, false)
                R.id.menu_project -> binding.viewPager.setCurrentItem(2, false)
                R.id.menu_my -> binding.viewPager.setCurrentItem(3, false)
                else -> binding.viewPager.setCurrentItem(0, false)
            }
            return@setOnItemSelectedListener true
        }


        val dispatcher = onBackPressedDispatcher
        dispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //返回键处理
                if (System.currentTimeMillis() - lastPressBackTime > 1000) {
                    "再按一次退出".toast()
                    lastPressBackTime = System.currentTimeMillis()
                } else {
                    finish()
                }
            }
        })
    }


}