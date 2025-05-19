package com.example.health.Apartment.Register


data class LoginRequest(
    val no: String,     // 学号
    val password: String // 密码
)

// ApiResponse.kt
data class ApiResponse<T>(
    val code: Int,         // 响应状态码（1成功，0失败）
    val msg: String?,      // 错误信息
    val data: T?           // 响应数据
)

// UserToken.kt
data class UserToken(
    val token: String      // JWT Token
)

// RegisterRequest.kt
data class RegisterRequest(
    val no: String,        // 学号
    val email: String,     // 邮箱
    val password: String,  // 密码
    val name: String,      // 姓名
    val gender: String     // 性别
)

// User.kt
data class User(
    val no: String,        // 学号
    val name: String,      // 姓名
    val gender: String,    // 性别
    val email: String,     // 邮箱
    val academy: String,   // 学院
    val major: String      // 专业
)

// UserProfile.kt
data class UserProfile(
    val no: String,        // 学号
    val name: String,      // 姓名
    val gender: String,    // 性别
    val email: String,     // 邮箱
    val academy: String,   // 学院
    val major: String      // 专业
)

// UpdateProfileRequest.kt
data class UpdateProfileRequest(
    val name: String,         // 姓名
    val gender: String,       // 性别（如："男"/"女"）
    val email: String,        // 邮箱
    val academy: String,      // 学院
    val major: String         // 专业
)

// MaintenanceResponse.kt
data class MaintenanceResponse(
    val requestId: Int        // 维修请求唯一标识（仅在成功时返回）
)

// MaintenanceItem.kt
data class MaintenanceItem(
    val id: Int,              // 维修记录ID
    val dorId: Int,           // 宿舍ID
    val description: String,  // 维修描述
    val urgency: Int,         // 紧急程度（1-低，2-中，3-高）
    val status: Int,          // 处理状态（0-待处理，1-处理中，2-已完成）
    val feedback: String?     // 维修反馈（可能为空）
)

data class AuthorDatas(
    val academy: String,
    val gender: String,
    val major: String,
    val name: String,
    val no: String
)

data class Register(
    val email: String,
    val no: String,
    val password: String
)

