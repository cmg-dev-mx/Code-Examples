package mx.dev.cmg.android.geminiai.source.ai

import javax.inject.Inject

class AISourceImpl @Inject constructor() : AISource {



    override suspend fun askQuestion(question: String): String {
        return "Response to: $question"
    }
}