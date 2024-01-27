@file:Suppress("DEPRECATION")

package com.example.multiplatformtest.android.screens

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.storage.FirebaseStorage
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.ui.PlayerView

//@RequiresApi(Build.VERSION_CODES.P)
//@Preview
//@Composable
//fun NotificationScreen() {
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        LazyColumn() {
////            for (i in 1..30) {
//            item {
//                DisplayImage()
//            }
////            }
//        }
//    }
//}


@OptIn(UnstableApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun displayImage(): Uri? {
    val context = LocalContext.current
    val storageRef = FirebaseStorage.getInstance().reference.child("weaponNew.mp4")
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var image by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(storageRef) {
        storageRef.downloadUrl.addOnSuccessListener { uri ->
            imageUri = uri
            Log.d("fburi", imageUri.toString())
        }
    } //    val image by FirebaseStorage.getInstance().getReference(imageUrl).get().observeAsState()
    return imageUri
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer() {
//    val sampleVideo = fbUr
    val context = LocalContext.current
    val player = SimpleExoPlayer.Builder(context).build()
    val playerView = PlayerView(context)
    val mediaItem = MediaItem.fromUri("https://firebasestorage.googleapis.com/v0/b/rajasthanpolice-bc516.appspot.com/o/weaponNew.mp4?alt=media&token=544d8be4-a262-4094-9e9e-6d110165c4ec")
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    player.setMediaItem(mediaItem)
    playerView.player = player
    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady

    }
    AndroidView(factory = {
        playerView
    })
}

