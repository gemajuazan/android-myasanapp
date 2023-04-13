package org.gemapp.myasanaapp.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.gemapp.appilates.R
import org.gemapp.myasanaapp.main.ui.theme.AppilatesTheme
import androidx.navigation.compose.rememberNavController
import org.gemapp.myasanaapp.food.FoodScreen
import org.gemapp.myasanaapp.main.model.Routes
import org.gemapp.myasanaapp.main.viewmodel.ExerciseViewModel
import org.gemapp.myasanaapp.settings.SettingsScreen
import org.gemapp.myasanaapp.sport.SportScreen

class MainActivity : ComponentActivity() {
    private val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppilatesTheme {
                MainApp()
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainApp() {
        val scaffoldState = rememberScaffoldState()
        val navigationControllerScaffold = rememberNavController()
        val navigationController = rememberNavController()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.onPrimary))
        ) {

            NavHost(navController = navigationController, startDestination = Routes.Main.route) {
                composable(Routes.Main.route) {
                    Scaffold(
                        topBar = {
                            Toolbar()
                        }, bottomBar = {
                            BottomAppNavigation(navigationController, navigationControllerScaffold)
                        }, scaffoldState = scaffoldState) {
                        NavHost(navController = navigationControllerScaffold, startDestination = Routes.Screen1.route) {
                            composable(Routes.Screen1.route) {
                                SportScreen(navigationController)
                            }
                            composable(Routes.Screen2.route) {
                                FoodScreen(navigationController)
                            }
                            composable(Routes.Screen3.route) {
                                SettingsScreen(navigationController)
                            }
                        }
                    }
                }
                composable(Routes.AddNewItem.route) {
                    AddNewItem(navigationController = navigationController, viewModel = exerciseViewModel)
                }
            }
        }
    }

    @Composable
    fun Toolbar() {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.myasanapp_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxWidth()
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                Divider(
                    color = colorResource(id = R.color.divider),
                    modifier = Modifier.align(Alignment.Center),
                )
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.divider_logo),
                    modifier = Modifier.align(Alignment.Center),
                    contentDescription = ""
                )
            }
        }
    }

    @Composable
    fun BottomAppNavigation(
        navigationController: NavHostController,
        navigationControllerScaffold: NavHostController
    ) {
        var index by remember { mutableStateOf(0) }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                elevation = 8.dp
            ) {
                BottomNavigation(
                    backgroundColor = colorResource(id = R.color.onPrimary)
                ) {
                    BottomAppNavigationItem(image = R.drawable.sport, selected = index == 0, onClick = {
                        index = 0
                        navigationControllerScaffold.navigate(Routes.Screen1.route)
                    })
                    BottomAppNavigationItem(image = R.drawable.food, selected = index == 1, onClick = {
                        index = 1
                        navigationControllerScaffold.navigate(Routes.Screen2.route)
                    })
                    BottomAppNavigationItem(
                        image = R.drawable.settings,
                        selected = index == 2,
                        onClick = {
                            index = 2
                            navigationControllerScaffold.navigate(Routes.Screen3.route)
                        })
                    BottomAppNavigationItem(image = -1, selected = index == 3, onClick = {
                        index = 3
                    })
                }
            }
            FloatingActionButton(
                backgroundColor = colorResource(id = R.color.primary),
                contentColor = colorResource(id = R.color.onPrimary),
                onClick = {
                    navigationController.navigate(Routes.AddNewItem.route) {
                        popUpTo(Routes.Main.route)
                    }
                }, modifier = Modifier
                    .padding(bottom = 32.dp, end = 24.dp)
                    .align(Alignment.BottomEnd)
                    .size(56.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.add),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }

    @Composable
    fun RowScope.BottomAppNavigationItem(image: Int, selected: Boolean, onClick: () -> Unit) {
        val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
        val ripple = rememberRipple(bounded = false, color = colorResource(id = R.color.primary))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .selectable(
                    selected = selected,
                    onClick = onClick,
                    role = Role.Tab,
                    interactionSource = interactionSource,
                    indication = ripple
                )
                .fillMaxHeight()
                .weight(1f)
        ) {
            if (selected && image != -1) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.divider_menu),
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary),
                    modifier = Modifier
                        .width(72.dp)
                        .padding(bottom = 8.dp)
                        .align(Alignment.TopCenter)
                )
            }

            if (image != -1) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = image),
                    contentDescription = "",
                    tint = if (selected) colorResource(id = R.color.primary) else colorResource(id = R.color.onSurfaceMedium),
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center)
                )
            } else {
                Box(modifier = Modifier.size(32.dp))
            }
        }
    }
}