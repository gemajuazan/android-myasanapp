package org.gemapp.myasanaapp.main.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.gemapp.appilates.R

val Domine = FontFamily(
    Font(R.font.domine_regular, FontWeight.Normal),
    Font(R.font.domine_bold, FontWeight.Bold),
    Font(R.font.domine_medium, FontWeight.Medium),
    Font(R.font.domine_semi_bold, FontWeight.SemiBold)
)

@Composable
fun Header(text: String) {
    Text(text = text, fontSize = 32.sp, fontFamily = Domine, fontWeight = FontWeight.SemiBold)
}

@Composable
fun Body1(text: String) {
    Text(text = text, fontSize = 14.sp, fontFamily = Domine, fontWeight = FontWeight.Normal)
}