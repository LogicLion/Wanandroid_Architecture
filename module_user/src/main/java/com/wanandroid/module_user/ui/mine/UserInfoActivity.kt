package com.wanandroid.module_user.ui.mine

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.core.view.setPadding
import androidx.databinding.adapters.ViewBindingAdapter.setPadding
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ColorUtils
import com.doreamon.treasure.ext.dp
import com.doreamon.treasure.utils.dip2px
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseActivity
import com.wanandroid.module_base.base.DataBindingConfig
import com.wanandroid.module_base.global.UserInfoData
import com.wanandroid.module_user.BR
import com.wanandroid.module_user.R
import com.wanandroid.module_user.databinding.UserActivityUserInfoBinding
import org.koin.android.ext.android.inject

/**
 * 用户资料页
 * @author wzh
 * @date 2023/7/26
 */
@Route(path = ModuleMineAPI.ROUTER_PATH_USER_INFO)
class UserInfoActivity : BaseActivity() {
    val viewModel: UserInfoViewModel by inject()
    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.user_activity_user_info, BR.viewModel, viewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getViewBinding<UserActivityUserInfoBinding>()

        binding.ivAvatar.apply {
            val shapeModel =
                ShapeAppearanceModel.builder().setAllCornerSizes(RelativeCornerSize(0.5f))
                    .build()

            shapeAppearanceModel = shapeModel
            strokeColor =
                ColorStateList.valueOf(
                    ColorUtils.getColor(com.doreamon.treasure.R.color.color_primary)
                )
            strokeWidth = dip2px(3)
            setPadding(1.5f.dp.toInt())
        }


        binding.tvLogout.setOnClickListener {
            viewModel.logout()
        }


        viewModel.avatarUrl.value = UserInfoData.value?.icon
        viewModel.nickName.value = UserInfoData.value?.nickname
        UserInfoData.observe(this) {
            if (it == null) {
                finish()
            } else {
                viewModel.avatarUrl.value = it.icon
                viewModel.nickName.value = it.nickname
            }
        }
    }
}