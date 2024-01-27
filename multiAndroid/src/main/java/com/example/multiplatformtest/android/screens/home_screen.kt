import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiplatformtest.android.app.NavItem
import com.example.multiplatformtest.android.screens.VideoPlayer
import com.example.multiplatformtest.android.screens.displayImage
import com.example.multiplatformtest.android.theme.customColor


@Composable
fun navBody(
    items: List<NavItem>,
    currentRoute: String?,
    onClick: (NavItem) -> Unit
) {
    items.forEachIndexed { index, navItem ->
        NavigationDrawerItem(
            label = { Text(text = navItem.title) },
            icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
            selected = currentRoute == navItem.route,
            onClick = { onClick(navItem) },
        )
    }
}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun YourComposableFunction() {
    val context = LocalContext.current
    var isComposableVisible by remember { mutableStateOf(false) }
    displayImage()
    Card(
        colors = CardDefaults.cardColors(containerColor = customColor.Beige),
        modifier = Modifier
            .height(550.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            text = "New Update "
        )
        Box(
            modifier = Modifier
                .height(300.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            VideoPlayer()
        }
        Text(
            modifier = Modifier.padding(top = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            text = "  city:   Anūpgarh\n\n  region:   Rajasthan\n\n country:   INDIA\n\n  location:  29.1911,73.2086"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = customColor.Vermilion),
                onClick = { Toast.makeText(context, "Know More", Toast.LENGTH_SHORT).show() },
            ) {
                Text(text = "Know more")
            }
        }
    }
}


//@RequiresApi(Build.VERSION_CODES.P)
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun HomeScreen() {
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//    val navController = rememberNavController()
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            ModalDrawerSheet(modifier = Modifier.width(intrinsicSize = IntrinsicSize.Min)) {
//                Image(
//                    painter = painterResource(id = R.drawable.rj_logo),
//                    modifier = Modifier
//                        .width(120.dp)
//                        .height(120.dp)
//                        .padding(start = 20.dp),
//                    contentDescription = null
//                )
//                Text("Rajasthan Police App", modifier = Modifier.padding(16.dp))
//                Divider()
//                NavigationDrawerItem(
//                    icon = {
//                        Icon(
//                            Icons.Default.Notifications,
//                            contentDescription = "Notification"
//                        )
//                    },
//                    label = { Text(text = "Notifications") },
//                    selected = false,
//                    onClick = {
//                        scope.launch {
//                            drawerState.apply {
//                                if (isClosed) open() else close()
//                            }
//                        }
//                    }
//                )
//                NavigationDrawerItem(
//                    icon = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.baseline_health_and_safety_24),
//                            contentDescription = "Notification"
//                        )
//                    },
//                    label = { Text(text = "Hospitals") },
//                    selected = false,
//                    onClick = {
//                        scope.launch {
//                            drawerState.apply {
//                                if (isClosed) open() else close()
//                            }
//                        }
//                    }
//                )
//                // ...other drawer items
//            }
//        }
//    ) {
//        // Screen content
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = {
//
//                        Text("RJ Police APP")
//
//                    },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            scope.launch {
//                                drawerState.apply {
//                                    if (isClosed) open() else close()
//                                }
//                            }
//                        }) {
//                            Icon(
//                                Icons.Default.Menu,
//                                contentDescription = "menu",
//                            )
//                        }
//                    },
//                    actions = {
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(Icons.Default.ArrowForward, contentDescription = null)
//                        }
//                    }
//
//                )
//            },
//
//            content = { innerPadding ->
//                // Main content of the screen goes here
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(innerPadding)
//                ) {
//                    YourComposableFunction()
////                    Card(
////                        colors = CardDefaults.cardColors(containerColor = customColor.Beige),
////                        modifier = Modifier
////                            .height(550.dp)
////                    ) {
////                        Text(
////                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
////                            fontSize = 20.sp,
////                            fontWeight = FontWeight.Bold,
////                            text = "New Update "
////                        )
////                        Box(
////                            modifier = Modifier
////                                .height(300.dp)
////                        ) {
////                            VideoPlayer()
////                        }
////                        Text(
////                            modifier = Modifier.padding(top = 10.dp),
////                            fontWeight = FontWeight.Bold,
////                            fontSize = 15.sp,
////                            text = "  city:   Anūpgarh\n\n  region:   Rajasthan\n\n country:   IN\n\n  location:  29.1911,73.2086"
////                        )
////                        Button(onClick = { isComposableVisible = !isComposableVisible }) {
////                            Text(text = "Button ")
////
////                        }
////                        if (isComposableVisible) {
//////                             Call your composable function here
////                            HospitalsList()
////                        }
////                    }
//
//                }
//            }
//        )
//    }
//}
//                        HospitalsList()
//                    NotificationScreen()
//                    Text("Hello, this is your main content with a custom drawer!")

