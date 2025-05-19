package com.example.health.Apartment.Register

import com.example.health.Apartment.MaintenanceRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

// Retrofit 接口定义
interface DormitoryApiService {
    @POST("/user/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<UserToken>

    @POST("/user/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<User>

    @GET("/user/getInfo")
    suspend fun getUserInfo(@Header("Authorization") token: String): ApiResponse<UserProfile>

    @POST("/user/updateInfo")
    suspend fun updateUserInfo(
        @Header("Authorization") token: String,
        @Body request: UpdateProfileRequest
    ): ApiResponse<Unit>

    @POST("/user/maintenance/add")
    suspend fun submitMaintenanceRequest(
        @Header("Authorization") token: String,
        @Body request: MaintenanceRequest
    ): ApiResponse<MaintenanceResponse>

    @GET("/user/maintenance/my")
    suspend fun getMyMaintenanceRequests(
        @Header("Authorization") token: String,
        @Query("status") status: Int? = null
    ): ApiResponse<List<MaintenanceItem>>

}