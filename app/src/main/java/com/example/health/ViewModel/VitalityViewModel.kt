package com.example.health.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VitalityViewModel : ViewModel() {
    //活力指标的三个数据值、日期
    private val _calories = MutableLiveData(237)
    private val _steps = MutableLiveData(3920)
    private val _midAcitivityTime = MutableLiveData(24)

    val calories : LiveData<Int>
        get() = _calories

    val steps : LiveData<Int>
        get() = _steps

    val midAcitivityTime : LiveData<Int>
        get() = _midAcitivityTime


    fun onCaloriesChanged(calories: Int){
//        val c = if(calories>=0){calories}else 0
        //没能解决负数也绘图
        val c = calories.takeIf { it >= 0 } ?: 0
        _calories.postValue(calories)
    }

    fun onStepsChanged(steps: Int){
        val c = steps.takeIf { it >= 0 } ?: 0
        _steps.postValue(steps)
    }

    fun onMidAcitivityTimeChanged(midAcitivityTime: Int){
        val c = midAcitivityTime.takeIf { it >= 0 } ?: 0
        _midAcitivityTime.postValue(midAcitivityTime)
    }
}