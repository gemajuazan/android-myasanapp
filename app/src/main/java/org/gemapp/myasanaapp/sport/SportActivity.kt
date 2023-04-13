package org.gemapp.myasanaapp.sport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.gemapp.appilates.R
import org.gemapp.myasanaapp.main.ui.Header
import org.gemapp.myasanaapp.sport.ui.theme.MyasanappTheme

class SportActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyasanappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AddNewView()
                }
            }
        }
    }

    @Composable
    fun AddNewView() {
        Box(modifier = Modifier.fillMaxSize()) {
            Toolbar()
        }
    }

    @Composable
    fun Toolbar() {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.padding(horizontal = 16.dp)) {
                IconButton(
                    onClick = { onBackPressedDispatcher.onBackPressed() },
                    modifier = Modifier.align(
                        Alignment.CenterStart
                    )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.go_back),
                        contentDescription = "Go back"
                    )
                }
                Header(text = "Nuevo ejercicio", modifier = Modifier.align(Alignment.Center))
            }
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
    fun AddNewAsanaView() {

    }

    @Composable
    fun AddNewFoodView() {

    }
}

