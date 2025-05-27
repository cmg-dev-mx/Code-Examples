package mx.dev.cmg.android.geminiai.source.ai

interface AISource {
    suspend fun askQuestion(question: String): String
}
