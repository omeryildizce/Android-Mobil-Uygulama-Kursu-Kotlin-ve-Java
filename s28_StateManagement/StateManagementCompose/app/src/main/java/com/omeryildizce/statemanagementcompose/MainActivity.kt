package com.omeryildizce.statemanagementcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.omeryildizce.statemanagementcompose.ui.theme.StateManagementComposeTheme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            StateManagementComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainScreen()

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateManagementComposeTheme {
        MainScreen()
    }
}


@Composable
fun MainScreen() {
    var myString by remember {
        mutableStateOf("")
    }
    var myPassword by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.background(Color.Magenta),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpecialText(string = "Test")
            Spacer(modifier = Modifier.padding(8.dp))
            SpecialText(string = "Helo World!")
            Spacer(modifier = Modifier.padding(8.dp))
            SpecialTextField(myString){
                myString = it
            }
            Spacer(modifier = Modifier.padding(8.dp))
            SpecialTextField(myPassword){
                 myPassword = it
            }
        }
    }
}

@Composable
fun SpecialText(string: String) {
    Text(
        text = string,
        fontSize = 20.sp,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.Monospace,
    )
}

@Composable
fun SpecialTextField( myString:String , function: (String)-> Unit){

    TextField(value = myString ,onValueChange = function )
}