package com.example.multiplatformtest.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiplatformtest.android.R
import com.example.multiplatformtest.android.theme.*
import com.example.multiplatformtest.android.theme.customColor.Primary


@Composable
fun HeadingTextComponent(value :String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.SansSerif
        ),
        textAlign = TextAlign.Center
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelInput :String){
    val textValue = remember{
        mutableStateOf("")
    }
    OutlinedTextField(
        label = {Text(text = labelInput)},
        value = textValue.value,
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShapes.large),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        keyboardOptions = KeyboardOptions.Default,
        singleLine = true,
        maxLines = 1,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.profile),
                contentDescription = "")
        }
    )
}

@Composable
fun PoliceLogo(){
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center)
         {
    Image(painter = painterResource(id = R.drawable.rj_logo),
        modifier = Modifier
            .width(120.dp)
            .height(120.dp),
        contentDescription = "")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelInput :String){
    val password = remember{
        mutableStateOf("")
    }
    val passwordVisibile = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        label = {Text(text = labelInput)},
        value = password.value,
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShapes.large),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        onValueChange = {
            password.value = it
        },
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.profile),
                contentDescription = "")
        },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                
            }
        }
    )
    
}
