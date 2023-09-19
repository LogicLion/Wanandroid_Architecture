package com.wanandroid.module_base.base

import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.SparseArray
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter


abstract class DataBindingActivity : AppCompatActivity() {
    private lateinit var mActivityProvider: ViewModelProvider
    private var mFactory: ViewModelProvider.Factory? = null
    private var mBinding: ViewDataBinding? = null
    protected val TAG = this.javaClass.simpleName

    protected abstract fun getDataBindingConfig(): DataBindingConfig


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBindingConfig: DataBindingConfig = getDataBindingConfig()

        //TODO tip: DataBinding 严格模式：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这样的方式，来彻底解决 视图调用的一致性问题，
        // 如此，视图刷新的安全性将和基于函数式编程的 Jetpack Compose 持平。

        // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
        val binding: ViewDataBinding =
            DataBindingUtil.setContentView(this, dataBindingConfig.layout)
        binding.lifecycleOwner = this

        if (dataBindingConfig.vmVariableId != -1
            && dataBindingConfig.stateViewModel != null
        ) {
            binding.setVariable(
                dataBindingConfig.vmVariableId,
                dataBindingConfig.stateViewModel
            )
        }

        val bindingParams: SparseArray<*> = dataBindingConfig.getBindingParams()

        bindingParams.forEach { key, any ->
            binding.setVariable(key, any)
        }
        mBinding = binding

        // 界面跳转
        dataBindingConfig.stateViewModel?.uiNavigationData?.observe(this) { path ->
            ARouter.getInstance().build(path).navigation(this@DataBindingActivity)
        }

    }


    open fun <T : ViewDataBinding> getViewBinding(): T {
        return mBinding as T
    }


    val isDebug: Boolean
        get() = applicationContext.applicationInfo != null &&
                (applicationContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

    protected fun showLongToast(text: String?) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    protected fun showShortToast(text: String?) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    protected fun showLongToast(stringRes: Int) {
        showLongToast(applicationContext.getString(stringRes))
    }

    protected fun showShortToast(stringRes: Int) {
        showShortToast(applicationContext.getString(stringRes))
    }


    protected open fun <T : ViewModel> getViewModel(modelClass: Class<T>): T {
        if (!::mActivityProvider.isInitialized) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider[modelClass]
    }

}

