package com.example.health.db.entity

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.health.Myhealth.Vitality.VitalityDataOfCalories
import java.util.Date


@Stable //基于观察者模式的一个状态，和mutable有类似的作用：状态更新时 UI会自动重组更新
data class VitalityState(
    var date : Date = Date() ,
    var calories : Int = 0,
    var steps : Int = 0,
    var midActivity : Int = 0,
    var isDelete : String = "0"
)

@Keep //防止实体类混淆，导致无法找到
@Entity(tableName = "Vitality_data")
class VitalityEntity {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "date")
    val date:  Date = Date()
    @ColumnInfo(name = "calories")
    val calories : Int = 0
    @ColumnInfo(name = "steps")
    val steps : Int = 0
    @ColumnInfo(name = "midActivity")
    val midActivity : Int = 0

}