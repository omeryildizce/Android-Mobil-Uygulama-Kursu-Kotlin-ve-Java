package com.omeryildizce.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omeryildizce.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                MainScreen()

            }
        }

    }


}

@Composable
fun MainScreen() {
    val colors = listOf(
        Color.Red,
        Color.Magenta,
        Color.Blue,
        Color.Green,
        Color.Yellow,
        Color.Cyan
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = Brush.linearGradient(colors)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomText(text = "Hello World!")
        CustomText(text = "Merhaba Dünya!")
        CustomText(text = "My world")
        CustomText(text = "My rules")

        Row() {
            CustomText(text = "My world")
            CustomText(text = "Go")
        }


    }
}

@Composable
fun CustomText(text: String) {

    Text(modifier = Modifier
        .padding(10.dp)
        .background(
            brush = Brush.linearGradient(colors = listOf(Color.Magenta, Color.Blue)),
            shape = CircleShape
        )
        .padding(10.dp)
        .clickable {
            println("Hello world")
        }
        .fillMaxWidth(0.7f),
        textAlign = TextAlign.Center,
        text = text,
        color = Color.White,
        fontSize = 25.sp,
        fontWeight = FontWeight.ExtraBold
    )
}

@Preview(name = "page1", showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTheme {
        MainScreen()
    }
}
