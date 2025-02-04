package mx.dev.cmg.android.jetai.utils

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun isValidEmail(email: String): Boolean {
    if (email.isEmpty()) return false
    val emailPattern = Patterns.EMAIL_ADDRESS
    return emailPattern.matcher(email).matches()
}

fun formatDate(date: Date): String {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(date)
}