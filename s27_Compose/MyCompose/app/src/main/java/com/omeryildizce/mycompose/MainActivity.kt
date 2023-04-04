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
import androidx.compose.ui.tooling.preview.Preview
import com.omeryildizce.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                Gretting("merhaba")

            }
        }
        
     }


}

@Composable
fun Gretting(key: String) {
    Text(text = "$key dünya")
}


@Preview(name = "page1", showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTheme {
        Gretting(key = "merhaba")

    }
}

@Preview(name = "page2", showBackground = true)
@Composable
fun Default1Preview() {
    MyComposeTheme {
        Gretting(key = "selam")

    }
}




