package com.wanandroid.module_main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.doreamon.treasure.base.BaseActivity
import com.doreamon.treasure.base.BaseViewModel
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_main.R
import com.wanandroid.module_main.databinding.MainActivityMainBinding

/**
 * @author wzh
 * @date 2023/5/22
 */
class MainActivity : BaseActivity<BaseViewModel>() {
    override fun setupLayoutId() = R.layout.main_activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = getViewBinding<MainActivityMainBinding>()
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 4

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    3 -> ModuleMineAPI.getMineFragment()
                    else -> HomeFragment()
                }
            }
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.menu_my -> binding.viewPager.setCurrentItem(3, false)
                else -> binding.viewPager.setCurrentItem(0, false)
            }
            return@setOnItemSelectedListener true
        }
    }
}