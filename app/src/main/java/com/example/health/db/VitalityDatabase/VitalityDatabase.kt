package com.example.health.db.VitalityDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.health.db.dao.VitalityDao
import com.example.health.db.entity.VitalityEntity


@Database(
    version = 1,
    entities = [
        VitalityEntity::class
    ]   //和表相互映射的实体类
)

abstract class VitalityDatabase:RoomDatabase() {
    abstract fun VitalityStateDao():VitalityDao
}

//数据库初始化配置类，需要先将数据库初始化才能使用。在Application中调用AppDB.init(Context)就可以初始化了
class AppDB{
    companion object{
        fun init(context: Context):VitalityDatabase{
            val databaseBuilder = Room.databaseBuilder(
                context = context,
                klass = VitalityDatabase::class.java,
                name = "VitalityDB"
            ).apply {
                fallbackToDestructiveMigration()
            }
            return databaseBuilder.build()
        }
    }
}
