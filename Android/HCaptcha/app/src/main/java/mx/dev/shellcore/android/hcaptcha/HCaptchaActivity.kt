package mx.dev.shellcore.android.hcaptcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hcaptcha.sdk.HCaptcha

class HCaptchaActivity : AppCompatActivity() {

    private val hCaptcha = HCaptcha.getClient(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hCaptcha.setup().verifyWithHCaptcha()
        hCaptcha.addOnSuccessListener { response ->
            val userResponseToken = response.tokenResult
            val intent = Intent()
            intent.putExtra("token", userResponseToken)
            setResult(RESULT_OK, intent)
            finish()
        }.addOnFailureListener { e ->
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}