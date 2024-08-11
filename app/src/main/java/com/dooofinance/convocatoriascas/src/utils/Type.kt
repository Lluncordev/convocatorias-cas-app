package com.dooofinance.convocatoriascas.src.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.unit.sp
import com.dooofinance.convocatoriascas.R

// Set of Material typography styles to start with
val Typography = Typography(

    //Logo
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.antonregular)),
        fontSynthesis = FontSynthesis.Style,
        fontSize = 35.sp,
    ),

    //Descripcion
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.outfitregular)),
        fontSize = 15.sp
    ),

    //Titulo del asunto
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.outfitsemibold)),
        fontSize = 18.sp,
    ),

    //Cuerpo
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.outfitregular)),
        fontSize = 16.sp
    )
)