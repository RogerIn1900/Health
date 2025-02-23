//package com.example.health.Room
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//
//@Dao
//interface UserDao {
//    @Insert
//    suspend fun insert(user: User)
//
//    @Query("SELECT * FROM user_table")
//    suspend fun getAllUsers(): List<User>
//
//    @Query("DELETE FROM user_table")
//    suspend fun deleteAll()
//}