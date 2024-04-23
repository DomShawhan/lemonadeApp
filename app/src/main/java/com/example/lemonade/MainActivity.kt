package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp(modifier = Modifier)
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var timesToSqueeze: Int = (2..4).random()
    var timesSqueezed: Int = 1

    val imageResource = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when(step) {
        1 -> R.string.select
        2 -> R.string.squeeze
        3 -> R.string.drink
        else -> R.string.restart
    }

    val descriptionResource = when(step) {
        1 -> R.string.lemon_tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.glass_of_lemonade_content_description
        else -> R.string.empty_glass_content_description
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .background(Color.Yellow)
                    .padding(15.dp)
                    .fillMaxWidth()
            )
            Column (
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = modifier) {
                    Button(
                        onClick = {
                            if(step == 4) {
                                step = 1
                                timesSqueezed = 0
                            } else if(step == 2 && timesSqueezed != timesToSqueeze) {
                                timesSqueezed++
                            } else {
                                step++
                            }
                        },
                        modifier = modifier.border(2.dp, Color(0xFFFF5733), shape = RoundedCornerShape(60.dp)),
                        shape = RoundedCornerShape(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                    ) {
                        Image(
                            painter = painterResource(imageResource),
                            contentDescription = stringResource(descriptionResource)
                        )
                    }
                }
                Text(
                    text = stringResource(textResource),
                    modifier = modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp(modifier = Modifier)
    }
}