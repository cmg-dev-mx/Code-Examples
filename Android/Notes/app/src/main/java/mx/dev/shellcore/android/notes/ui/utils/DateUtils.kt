package mx.dev.shellcore.android.notes.ui.utils

import android.icu.text.SimpleDateFormat
import java.util.Locale

fun Long.formatShort(): String = try {
    SimpleDateFormat("MMM dd", Locale.getDefault()).format(this)
} catch (e: Exception) {
    ""
}