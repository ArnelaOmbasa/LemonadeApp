package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonAdeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonAdeTheme {

                    LemonApp()
                }
            }
        }
    }

@Composable
fun LemonApp(){
    var currentStep by remember {
        mutableStateOf(1)
    }
    var lemonSqueezeCount by remember {
        mutableStateOf(1)
    }
    when(currentStep){
        1 -> {LemonImageAndText( R.string.lemon_tree,R.drawable.lemon_tree,"Lemon Tree",{currentStep=2
            lemonSqueezeCount=(1..4).random()})}
        2 -> {LemonImageAndText( R.string.lemon_tree,R.drawable.lemon_squeeze,"Lemon",{lemonSqueezeCount--
            if (lemonSqueezeCount==0){currentStep=3} })}
        3 -> {LemonImageAndText( R.string.lemon_tree,R.drawable.lemon_drink,"Lemon Glass",{currentStep=4})}
        4 -> {LemonImageAndText( R.string.lemon_tree,R.drawable.lemon_restart,"Empty Glass",{currentStep=1})}

    }

}
@Composable
fun LemonImageAndText(text: Int,
        imageLocation: Int,
        imageDescription: String,
        onImageClick: () -> Unit,
        modifier: Modifier=Modifier){
    var image = painterResource(id = imageLocation)
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text= stringResource(id = text), fontSize = 18.sp)
        Spacer(modifier = modifier.height(16.dp))
    Image(painter = image,
        contentDescription =imageDescription,
        modifier= modifier
            .border(BorderStroke(2.dp, color = Color.Blue), shape = RoundedCornerShape(5.dp))
            .padding(16.dp)
            .wrapContentSize()
            .clickable(onClick = onImageClick)
    )    }

    }
@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    LemonAdeTheme {
        LemonApp()
    }
}
