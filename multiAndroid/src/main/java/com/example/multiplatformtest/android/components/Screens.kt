package com.example.multiplatformtest.android.components

sealed class Screens(var route:String) {
    object Notifications :Screens("Notifications")
    object Hospitals :Screens("Hospitals")
}
