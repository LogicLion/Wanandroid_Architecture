package com.wanandroid.module_base.base

import android.os.Bundle
import android.util.Log


/**
 * @author wzh
 * @date 2022/2/25
 */
abstract class BaseFragment: DataBindingFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG,"onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG,"onDestroy")
    }
}