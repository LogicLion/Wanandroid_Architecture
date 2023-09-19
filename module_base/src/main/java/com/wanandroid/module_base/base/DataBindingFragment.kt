package com.wanandroid.module_base.base

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author wzh
 * @date 2022/2/23
 */
abstract class DataBindingFragment : Fragment() {
    private lateinit var mActivityProvider: ViewModelProvider

    /** 根布局对象 */
    protected var rootView: View? = null

    /** DataBinding 对象 */
    protected lateinit var mBinding: ViewDataBinding

    /** 当前界面 Context 对象*/
    protected lateinit var mContext: FragmentActivity

    protected val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 保存当前 Context 对象
        mContext = requireActivity()
    }


    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (null == rootView) {

            val dataBindingConfig = getDataBindingConfig()
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, dataBindingConfig.layout, container, false)
            binding.lifecycleOwner = this
            binding.setVariable(
                dataBindingConfig.vmVariableId,
                dataBindingConfig.stateViewModel
            )
            val bindingParams: SparseArray<*> = dataBindingConfig.getBindingParams()
            for (i in 0 until bindingParams.size()) {
                binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
            }
            mBinding = binding

            rootView = mBinding.root

            // 界面跳转
            dataBindingConfig.stateViewModel?.uiNavigationData?.observe(viewLifecycleOwner) { path ->
                ARouter.getInstance().build(path).navigation(mContext)
            }

            // 初始化布局
            initView()
        } else {
            (rootView?.parent as? ViewGroup?)?.removeView(rootView)
        }

        return rootView
    }

    fun <T : ViewDataBinding> getViewBinding(): T {
        return mBinding as T
    }


    protected open fun <T : ViewModel> getViewModel(modelClass: Class<T>): T {
        if (!::mActivityProvider.isInitialized) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider[modelClass]
    }


    /**
     * 初始化布局
     */
    abstract fun initView()


}