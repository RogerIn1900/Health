package com.example.health.Myhealth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.health.R

@Composable
fun StepNumber() {
    Image(
        painter = painterResource(id = R.mipmap.calorie),
        contentDescription = "",
        modifier = Modifier.size(20.dp)
    )
}