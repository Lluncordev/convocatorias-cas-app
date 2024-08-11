package com.dooofinance.convocatoriascas.src.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardSearch (
    active: Boolean,
    query: String,
    onQueryChange: (String) -> Unit,
    iconId: Int,
    placeholder: String,
    onClick: () -> Unit
){
    val keyboardController = LocalSoftwareKeyboardController.current

    if (!active){
        Column (
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onClick()
                }
                .padding(
                    top = 15.dp,
                    end = 10.dp,
                    start = 10.dp,
                    bottom = 7.dp
                ),
            verticalArrangement = Arrangement.Center
        ){
            DetailCardRow(
                iconId = iconId,
                contentDescription = "buscardor de convocatorias",
                text = placeholder )
        }
    } else {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,

            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),

            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),

            decorationBox = { innerTextField ->

                Row (
                    Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainer,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(iconId),
                        contentDescription = "Buscar por carrera o locacion",
                        modifier = Modifier
                            .size(26.dp)
                            .padding(end = 10.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )

                    Box {

                        if (query.isEmpty()) {
                            Text( text = placeholder,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            )
                        }

                        innerTextField()
                    }
                }
            },

        )
    }
}
