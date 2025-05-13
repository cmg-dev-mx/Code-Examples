package mx.dev.shellcore.android.widget.ui.widget

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import mx.dev.shellcore.android.widget.MainActivity

class MyAppWidget: GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            MyContent()
        }
    }

    @Composable
    fun MyContent() {
        Column(
            modifier = GlanceModifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant),
            verticalAlignment = Alignment.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Where to?",
                modifier = GlanceModifier.padding(12.dp)
            )
            Row(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    text = "Home",
                    onClick = actionStartActivity<MainActivity>()
                )
                Button(
                    text = "Work",
                    onClick = actionStartActivity<MainActivity>()
                )
            }
        }
    }
}
