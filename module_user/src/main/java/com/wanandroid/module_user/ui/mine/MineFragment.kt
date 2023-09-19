package com.wanandroid.module_user.ui.mine

import android.content.res.ColorStateList
import androidx.core.view.setPadding
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ColorUtils.getColor
import com.doreamon.treasure.ext.dp
import com.doreamon.treasure.utils.dip2px
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import com.wanandroid.module_base.arouter.api.ModuleMineAPI
import com.wanandroid.module_base.base.BaseFragment
import com.wanandroid.module_base.base.DataBindingConfig
import com.wanandroid.module_base.global.UserInfoData
import com.wanandroid.module_user.BR
import com.wanandroid.module_user.R
import com.wanandroid.module_user.databinding.UserFragmentMineBinding
import org.koin.android.ext.android.inject

/**
 * @author wzh
 * @date 2023/5/25
 */
@Route(path = ModuleMineAPI.ROUTER_MINE_MINE_FRAGMENT)
class MineFragment : BaseFragment() {
    val viewModel: MineViewModel by inject()
    override fun getDataBindingConfig() =
        DataBindingConfig(R.layout.user_fragment_mine, BR.viewModel, viewModel)

    override fun initView() {

        val binding = getViewBinding<UserFragmentMineBinding>()
        binding.ivAvatar.apply {
            val shapeModel =
                ShapeAppearanceModel.builder().setAllCornerSizes(RelativeCornerSize(0.5f))
                    .build()

            shapeAppearanceModel = shapeModel
            strokeColor =
                ColorStateList.valueOf(
                    getColor(com.doreamon.treasure.R.color.color_primary)
                )
            strokeWidth = dip2px(3)
            setPadding(1.5f.dp.toInt())
        }

        binding.ivAvatar.setOnClickListener {
//            viewModel.uiNavigationData.value = ModuleMineAPI.ROUTER_PATH_USER_INFO

            ARouter.getInstance().build(ModuleMineAPI.ROUTER_PATH_USER_INFO).navigation(mContext)
        }
        binding.tvNickname.setOnClickListener {
            ARouter.getInstance().build(ModuleMineAPI.ROUTER_PATH_USER_INFO).navigation(mContext)
        }


        UserInfoData.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.avatarUrl.value = it.icon
                viewModel.nickName.value = it.nickname
            } else {
                viewModel.nickName.value = "未登录"
            }
        }
    }
}