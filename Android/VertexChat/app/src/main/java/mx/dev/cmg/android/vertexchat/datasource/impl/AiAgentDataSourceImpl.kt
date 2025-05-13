package mx.dev.cmg.android.vertexchat.datasource.impl

import com.google.firebase.Firebase
import com.google.firebase.vertexai.type.content
import com.google.firebase.vertexai.vertexAI
import mx.dev.cmg.android.vertexchat.repository.datasource.AiAgentDataSource
import javax.inject.Inject

class AiAgentDataSourceImpl @Inject constructor() : AiAgentDataSource {

    private val model = Firebase.vertexAI.generativeModel("gemini-2.0-flash") // TODO Mover a Remote Config

    private val chat by lazy {
        val paymentCard = listOf(
            "Visa ending in 1234",
            "Mastercard ending in 5678",
            "American Express ending in 9012",
            "American Express ending in 1357"
        )

        val fullAmount = 123.45

        val request = """
            "Act as a customer service agent and help the customer make a payment.
            1. Determine which card they want to use (credit cards on list: $paymentCard - be sure
            to mention the first is the default card). 
            2. Ask if they want to pay the full amount of $fullAmount or a partial amount.
            3. When you are able to collect the required information present the selected card and 
            payment amount, and ask for a confirmation to proceed with the payment.
            3. When the user confirms the movement, return the following data: DATA:{amount: 
            *amount the user wants to pay*, card: XXXX}.
            Keep interactions very short and simple but provide options if applicable.
            Request 1 piece of information at a time."
        """.trimIndent()

        model.startChat(
            history = listOf(
                content(role = "user") { text(request) },
                content(role = "model") { text("Sure! I can help you with that.") }
            )
        )
    }

    override suspend fun queryPrompt(prompt: String): String {
        val response = chat.sendMessage(prompt)
        return response.text ?: "Error in querying!"
    }
}