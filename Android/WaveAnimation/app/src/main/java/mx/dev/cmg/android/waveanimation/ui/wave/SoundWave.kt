package mx.dev.cmg.android.waveanimation.ui.wave

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun SoundWaveVisualizer() {

    var soundIntensity by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = Unit) {
        while(true) {
            soundIntensity = Random.nextFloat() * 0.8f + 0.2f
            delay(200)
        }
    }

    SoundWaveCanvas(soundIntensity = soundIntensity)

}

@Composable
fun SoundWaveCanvas(soundIntensity: Float) {
    val waveColor = MaterialTheme.colorScheme.primary
    val amplitude = remember(soundIntensity) { 30.dp * soundIntensity }
    val frequency = 1.5f
    val animationSpeed = 0.08f

    val phase = remember { Animatable(0f) }

    LaunchedEffect(key1 = Unit) {
        phase.animateTo(
            targetValue = 2 * Math.PI.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        val width = size.width
        val height = size.height
        val centerY = height / 2

        val path = Path()
        path.moveTo(0f, centerY)

        for (x in 0..width.toInt()) {
            val y = centerY + amplitude.toPx() * sin(frequency * x / width * 2 * Math.PI + phase.value)
            path.lineTo(x.toFloat(), y.toFloat())
        }

        drawPath(
            path = path,
            color = waveColor,
            style = Stroke(width = 3.dp.toPx())
        )
    }
}
