package com.example.multiplatformtest.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multiplatformtest.android.R
import com.example.multiplatformtest.android.components.HeadingTextComponent
import com.example.multiplatformtest.android.components.MyTextField
import com.example.multiplatformtest.android.components.PoliceLogo

@Composable
fun SignUpScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Column {
0
//        HeadingTextComponent(value = stringResource(R.string.Head_text))
        HeadingTextComponent(value = stringResource(R.string.Create_ACC))

            Spacer(modifier = Modifier.padding(16.dp))  // 16dp
            PoliceLogo()
            Spacer(modifier = Modifier.padding(16.dp))  // 16dp
            MyTextField(labelInput = "First Name")
            MyTextField(labelInput = "Last Name")
            MyTextField(labelInput = "Email")
            MyTextField(labelInput = "passowrd")
        }
    }
}

@Preview
@Composable
fun DefaultV() {
    SignUpScreen()
}