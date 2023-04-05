package com.omeryildizce.statemanagementcompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtherScreen(name: String)    {
    var myString by remember {
        mutableStateOf("Hello Android")
    }
    Surface(color = Color.LightGray) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = myString,
                fontSize = 26.sp
            )
            Button(onClick = { }, colors = ButtonDefaults.buttonColors(Color.Blue)) {
//              Text(text = "Hello!", color = Color.White)
                Spacer(modifier = Modifier.padding(start = 2.dp))
                Text(text = "Hi!", color = Color.Magenta)
            }
            Spacer(modifier = Modifier.padding(4.dp))

            var myInteger by remember {
                mutableStateOf(10)
            }

            TextField(value = myString, onValueChange = {
                myString = it
            })
            Spacer(modifier = Modifier.padding(4.dp))

        }
    }


}

@Composable
private fun cardDesign() {
    var expanded = remember {
        mutableStateOf(false)
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card {
            Column(
                modifier = Modifier
                    .clickable {
                        expanded.value = !expanded.value
                    }
                    .fillMaxSize(0.90f)
            ) {
                Image(
                    painterResource(id = R.drawable.logo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color.Blue,
                                    Color.Black
                                )
                            )
                        )
                        .padding(16.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",

                    )
                Spacer(modifier = Modifier.padding(10.dp))
                AnimatedVisibility(visible = expanded.value) {
                    Text(
                        text = "Jetpack Compose",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = Color.Blue
                    )
                }
            }

        }
    }
}



