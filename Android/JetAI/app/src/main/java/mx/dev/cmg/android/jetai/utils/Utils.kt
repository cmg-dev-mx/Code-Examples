package mx.dev.cmg.android.jetai.utils

import android.util.Patterns

fun isValidEmail(email: String): Boolean {
    if (email.isEmpty()) return false
    val emailPattern = Patterns.EMAIL_ADDRESS
    return emailPattern.matcher(email).matches()
}