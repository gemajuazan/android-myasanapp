package org.gemapp.myasanaapp.main.ui

import android.annotation.SuppressLint
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.gemapp.appilates.R

val Domine = FontFamily(
    Font(R.font.domine_regular, FontWeight.Normal),
    Font(R.font.domine_bold, FontWeight.Bold),
    Font(R.font.domine_medium, FontWeight.Medium),
    Font(R.font.domine_semi_bold, FontWeight.SemiBold)
)

@SuppressLint("ModifierParameter")
@Composable
fun Header(text: String, color: Color = colorResource(id = R.color.primary), modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 22.sp,
        fontFamily = Domine,
        fontWeight = FontWeight.SemiBold,
        color = color,
        modifier = modifier
    )
}

@SuppressLint("ModifierParameter")
@Composable
fun Body1(text: String, color: Color = colorResource(id = R.color.onSurfaceMedium), modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontFamily = Domine,
        fontWeight = FontWeight.Normal,
        color = color,
        modifier = modifier
    )
}

@SuppressLint("ModifierParameter")
@Composable
fun Body2(text: String, color: Color = colorResource(id = R.color.onSurfaceMedium), textAlign: TextAlign = TextAlign.Center, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontFamily = Domine,
        fontWeight = FontWeight.Normal,
        textAlign = textAlign,
        color = color,
        modifier = modifier
    )
}