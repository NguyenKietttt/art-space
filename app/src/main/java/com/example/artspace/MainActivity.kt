package com.example.artspace

import android.graphics.BlurMaskFilter
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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import java.util.concurrent.atomic.AtomicInteger

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
    Column(modifier = Modifier.fillMaxSize()) {
        var currentPictureID by remember { mutableIntStateOf(0) }

        MainImage(
            currentPictureID,
            modifier = modifier
                .weight(0.7f)
                .shadow(Color.Gray, 15.dp, 70.dp, 20.dp)
        )
        ImageDescription(currentPictureID, modifier = modifier.weight(0.2f))
        ControllerButtons(
            onPreviousPictureClick = {
                currentPictureID -= 1
                if (currentPictureID < 0) {
                    currentPictureID = 0
                };
            },
            onNextPictureClick = {
                currentPictureID += 1
                if (currentPictureID > 2) {
                    currentPictureID = 2
                };
            },
            modifier = modifier.weight(0.1f)
        )
    }
}

@Composable
fun MainImage(currentPictureID: Int, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 72.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(getImageResource(currentPictureID)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(36.dp)
                .fillMaxSize()
        )
    }
}

@Composable
fun ImageDescription(currentPictureID: Int, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
            .background(Color(0xFFECEBF3))
    ) {
        Text(
            text = stringResource(getImageName(currentPictureID)),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = getImageCrateYear(currentPictureID).toString())
    }
}

@Composable
fun ControllerButtons(
    onPreviousPictureClick: () -> Unit,
    onNextPictureClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                onPreviousPictureClick.invoke()
            },
            modifier = Modifier
                .padding(16.dp)
                .weight(1.0f)
        ) {
            Text(text = "Previous")
        }

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            onClick = {
                onNextPictureClick.invoke()
            },
            modifier = Modifier
                .padding(16.dp)
                .weight(1.0f)
        ) {
            Text(text = "Next")
        }
    }
}

fun getImageResource(id: Int): Int {
    return when (id) {
        1 -> R.drawable.lemonade
        2 -> R.drawable.tender
        else -> R.drawable.avatar
    }
}

fun getImageName(id: Int): Int {
    return when (id) {
        1 -> R.string.lemonade_name
        2 -> R.string.tender_name
        else -> R.string.avatar_name
    }
}

fun getImageCrateYear(id: Int): Int {
    return when (id) {
        1 -> 2018
        2 -> 2019
        else -> 2017
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

fun Modifier.shadow(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            frameworkPaint.color = color.toArgb()

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()
            val rightPixel = size.width - leftPixel
            val bottomPixel = size.height

            canvas.drawRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                paint = paint,
            )
        }
    }
)