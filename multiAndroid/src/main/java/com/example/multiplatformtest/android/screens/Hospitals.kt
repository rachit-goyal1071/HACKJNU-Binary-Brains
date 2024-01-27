package com.example.multiplatformtest.android.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.multiplatformtest.android.R
import com.example.multiplatformtest.android.theme.customColor

@Preview
@Composable
fun HospitalsList() {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp), horizontalAlignment = Alignment.Start
    ) {
        for (i in 1..10) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = customColor.antiFlashWhite),
                    modifier = Modifier
                        .height(325.dp)
                        .padding(15.dp)
                        .shadow(5.dp, shape = RoundedCornerShape(10.dp))
                ) {
                    Text(
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp, bottom = 5.dp),
                        text = "Rungta Hospital",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            modifier = Modifier
                                .width(220.dp)
                                .padding(top = 5.dp)
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    color = Color.Gray
                                )
                                .clip(shape = RoundedCornerShape(10.dp)),
                            painter = painterResource(id = R.drawable.rungta),

                            contentDescription = null
                        )

                    }
                    SpannableAddressText()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(colors = ButtonDefaults.buttonColors(containerColor = customColor.Vermilion),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(start = 30.dp, end = 30.dp),
                            onClick = { openDialer(context, "7017946250") }) {
                            Icon(
                                Icons.Default.Call,
                                modifier = Modifier.size(20.dp),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(fontSize = 15.sp, text = "Call")

                        }
                        Button(colors = ButtonDefaults.buttonColors(containerColor = customColor.Vermilion),
                            contentPadding = PaddingValues(start = 30.dp, end = 30.dp),
                            shape = RoundedCornerShape(10.dp),
                            onClick = { sendEmail(context) }) {
                            Icon(
                                Icons.Default.Create,
                                modifier = Modifier.size(20.dp),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(text = "Message")

                        }
                    }
//
                }
            }
        }
    }
}

fun openDialer(context: Context, phoneNumber: String) {
    val u = Uri.parse("tel:$phoneNumber")
    val intent = Intent(Intent.ACTION_DIAL, u)

    try {
        startActivity(context, intent, null)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
    }
}

fun sendEmail(context: Context) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        this.putExtra(Intent.EXTRA_EMAIL, arrayOf("aarohisaxena2805@gmail.com"))
        this.putExtra(Intent.EXTRA_SUBJECT, "Urgent Requirement from Rajasthan Police")
        this.putExtra(Intent.EXTRA_TEXT, "textMessage")
        this.type = "message/rfc822"
    }
    try {
        startActivity(context, sendIntent, null)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
    }
    // Define what your app should do if no activity can handle the intent.
}

@Composable
fun SpannableAddressText() {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
            append("Address :")
        }
        withStyle(style = SpanStyle(Color.Black)) {
            append("Calgiri Marg, near Police Station, Jhalana Gram, Malviya Nagar, Jaipur, Rajasthan 302017")
        }
    }
    Text(modifier = Modifier.padding(top = 10.dp, start = 5.dp), text = annotatedString)

}


@Composable
fun SpannableNameText() {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
            append("Name :")
        }
        withStyle(style = SpanStyle(Color.Blue)) {
            append(" Rungta Hospital")
        }
    }
    Text(text = annotatedString)
}
