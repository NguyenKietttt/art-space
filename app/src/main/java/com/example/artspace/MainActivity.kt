package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        MainImage(modifier = modifier.weight(0.7f))
        ImageDescription(modifier = modifier.weight(0.2f))
        ControllerButtons(modifier = modifier.weight(0.1f))
    }
}

@Composable
fun MainImage(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 72.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(36.dp)
                .fillMaxSize()
        )
    }
}

@Composable
fun ImageDescription(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Cyan)
    ) {
        Text(text = "Kndt")
        Text(text = "2017")
    }
}

@Composable
fun ControllerButtons(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .weight(1.0f),
            onClick = { /*TODO*/ })
        {
            Text(text = "Previous")
        }

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            modifier = Modifier
                .padding(16.dp)
                .weight(1.0f),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArSpaceApp() {
    ArtSpaceTheme {
        Scaffold { innerPadding ->
            ArtSpaceLayout(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}