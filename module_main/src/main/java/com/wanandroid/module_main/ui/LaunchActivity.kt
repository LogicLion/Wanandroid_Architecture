package com.wanandroid.module_main.ui

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.wanandroid.module_base.base.BaseActivity
import com.wanandroid.module_base.base.BaseViewModel
import com.doreamon.treasure.ext.startTargetActivity
import com.doreamon.treasure.utils.dip2px
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.wanandroid.module_main.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author wzh
 * @date 2023/5/18
 */
class LaunchActivity : BaseActivity<BaseViewModel>() {
    override fun setupLayoutId() = R.layout.main_activity_launch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        val ivLogo = findViewById<ShapeableImageView>(R.id.iv_logo)

        ivLogo.apply {
            val shapeModel =
                ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, dip2px(30))
                    .build()

            shapeAppearanceModel = shapeModel
            strokeColor =
                ColorStateList.valueOf(
                    getColor(R.color.main_purple_200)
                )
            strokeWidth = dip2px(3)
        }


        lifecycleScope.launch {
            delay(500)

            startTargetActivity<MainActivity>()


            finish()
        }

    }
}