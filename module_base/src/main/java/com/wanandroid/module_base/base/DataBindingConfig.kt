package com.wanandroid.module_base.base

import android.util.SparseArray
import androidx.lifecycle.ViewModel

/**
 * @author wzh
 * @date 2023/6/2
 */
class DataBindingConfig(val layout: Int, val vmVariableId: Int, val stateViewModel: ViewModel?) {


    private val bindingParams: SparseArray<Any> = SparseArray<Any>()


    fun getBindingParams(): SparseArray<*> {
        return bindingParams
    }

    fun addBindingParam(
        variableId: Int,
        obj: Any?
    ): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, obj)
        }
        return this
    }
}