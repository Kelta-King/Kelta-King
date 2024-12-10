package com.example.testing

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                DrawingCanvas()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrawingCanvas(modifier: Modifier = Modifier) {
    val lastTouchX = remember {
        mutableStateOf(0f)
    }
    val lastTouchY = remember {
        mutableStateOf(0f)
    }
    val path = remember {
        mutableStateOf<Path?>(Path())
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
                .pointerInteropFilter {
                        motionEvent ->
                    when(motionEvent.action) {

                        MotionEvent.ACTION_DOWN -> {
                            path.value?.moveTo(motionEvent.x, motionEvent.y)
                            lastTouchX.value = motionEvent.x
                            lastTouchY.value = motionEvent.y
                        }

                        MotionEvent.ACTION_MOVE,MotionEvent.ACTION_UP -> {
                            val historySize = motionEvent.historySize
                            for (i in 0 until historySize) {
                                val historicalX = motionEvent.getHistoricalX(i)
                                val historicalY = motionEvent.getHistoricalY(i)

                                path.value?.lineTo(historicalX, historicalY)
                            }
                            path.value?.lineTo(motionEvent.x, motionEvent.y)
                            lastTouchX.value = motionEvent.x
                            lastTouchY.value = motionEvent.y
                        }
                    }
                    lastTouchX.value = motionEvent.x
                    lastTouchY.value = motionEvent.y
                    val tempPath = path.value
                    path.value = null
                    path.value = tempPath
                    true
                },
            onDraw = {
                path.value?.let { it ->
                    drawPath(
                        path = it,
                        color = Color.Black,
                        style = Stroke(
                            width = 4.dp.toPx()
                        ))
                }
            }
        )
    }
}

@Composable
fun DrawingCanvas2(modifier: Modifier = Modifier) {
    val path = remember { mutableStateOf(Path()) }
    val lastTouchPosition = remember { mutableStateOf(Offset(0f, 0f)) }
    val previousTouchPosition = remember { mutableStateOf(Offset(0f, 0f)) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        // Calculate new touch position based on the drag amount
                        val newPosition = lastTouchPosition.value + dragAmount

                        // Add the line from the last position to the new position
                        path.value.lineTo(previousTouchPosition.value.x, previousTouchPosition.value.y)

                        // Optionally, create a curved end by adding a quadratic Bézier curve
                        // We will use a control point to curve the path
                        if (previousTouchPosition.value != Offset(0f, 0f)) {
                            path.value.quadraticBezierTo(
                                previousTouchPosition.value.x, previousTouchPosition.value.y,
                                newPosition.x, newPosition.y
                            )
                        }

                        // Update the last touch position
                        previousTouchPosition.value = lastTouchPosition.value
                        lastTouchPosition.value = newPosition
                    }
                },
            onDraw = {
                // Draw the path on the canvas
                path.value.let {
                    drawPath(
                        path = it,
                        color = Color.Black,
                        style = Stroke(width = 4.dp.toPx()) // Stroke with a width of 4.dp
                    )
                }
            }
        )
    }
}
