package org.gemapp.myasanaapp.sport

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.gemapp.myasanaapp.main.ui.Body1

@Composable
fun SportScreen(navigationController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Body1(text = "Yoga")
    }
}
