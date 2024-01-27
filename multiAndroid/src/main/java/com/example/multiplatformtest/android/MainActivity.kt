package com.example.multiplatformtest.android

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.multiplatformtest.android.app.NavItem
import com.example.multiplatformtest.android.app.PoliceApp
import com.example.multiplatformtest.android.components.Navgraph
import com.example.multiplatformtest.android.components.Screens
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import navBody

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val items = listOf(
                NavItem(
                    title = "Notifications",
                    route = Screens.Notifications.route,
                    icon = Icons.Default.Notifications
                ),
                NavItem(
                    title = "Hospitals",
                    route = Screens.Hospitals.route,
                    icon = Icons.Default.Place
                )
            )
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet(modifier = Modifier.width(intrinsicSize = IntrinsicSize.Min)) {
                        Image(
                            painter = painterResource(id = R.drawable.rj_logo),
                            modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                                .padding(start = 20.dp),
                            contentDescription = null
                        )
                        Text("Rajasthan Police App", modifier = Modifier.padding(16.dp))
                        Divider(modifier = Modifier.padding(bottom = 10.dp))
                        navBody(items = items, currentRoute = currentRoute) {
                            navController.navigate(it.route)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }
                },
                drawerState = drawerState
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("RJ Police APP")

                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }) {
                                    Icon(
                                        Icons.Default.Menu,
                                        contentDescription = "menu",
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Default.ArrowForward, contentDescription = null)
                                }
                            }

                        )
                    },
                    content = { innerPadding ->
                        Navgraph(navController = navController, innerPaddingValues = innerPadding)
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                        }
                    }
                )
            }


//            HomeScreen()

            FirebaseMessaging.getInstance().token
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("newtoken", "getInstanceId failed", task.exception)
                        return@addOnCompleteListener
                    }

                    // Get the FCM token
                    val token = task.result
                    Log.d("newtoken", "FCM Token: $token")
                }
//                PoliceApp()
//            }
        }
    }
}


//@Composable
//fun GreetingView(text: String) {
//    Text(text = text)
//}