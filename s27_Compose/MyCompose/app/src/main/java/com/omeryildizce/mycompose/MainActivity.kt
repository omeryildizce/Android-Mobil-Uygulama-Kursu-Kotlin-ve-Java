package com.omeryildizce.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
fun MainScreen(){
     Text(
        text = "Merhaba!",
        color = MaterialTheme.colors.error,
        fontSize = 25.sp,
        fontWeight = FontWeight.ExtraBold
    )

    Text(
        text = "Merhaba Dünya!",
        color = MaterialTheme.colors.error,
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
