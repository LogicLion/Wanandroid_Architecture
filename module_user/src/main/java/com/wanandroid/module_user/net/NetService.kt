package com.wanandroid.module_user.net

import com.wanandroid.module_base.entity.NetResult
import com.wanandroid.module_base.entity.UserInfoEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author wzh
 * @date 2023/7/25
 */
interface NetService {

    /** 登录 */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): NetResult<UserInfoEntity>

    /** 注册 */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") pwd: String,
        @Field("repassword") pwdSure: String
    ): NetResult<Any?>


    /** 用户退出登录 */
    @GET("/user/logout/json")
    suspend fun logout(): NetResult<Any>


}