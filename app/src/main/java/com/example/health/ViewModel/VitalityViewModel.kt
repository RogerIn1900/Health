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
        _calories.postValue(calories)
    }

    fun onStepsChanged(steps: Int){
        _steps.postValue(steps)
    }

    fun onMidAcitivityTimeChanged(midAcitivityTime: Int){
        _midAcitivityTime.postValue(midAcitivityTime)
    }
}