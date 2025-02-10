package mx.dev.cmg.android.jetai.data.repository

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.cmg.android.jetai.Graph
import mx.dev.cmg.android.jetai.data.models.ModelName
import mx.dev.cmg.android.jetai.utils.Response

class PhotoReasoningRepository(
    private val generativeModel: GenerativeModel = Graph.generativeModel(ModelName.MULTIMODAL.modelName)
) {

    suspend fun reason(
        userInput: String,
        selectedImages: List<Bitmap>
    ): Flow<Response<String>> = flow {
        val prompt = "Look at the image(s) and then answer the following question: $userInput"
        try {
            emit(Response.Loading())
            val inputContent = content {
                for (bitmap in selectedImages) {
                    image(bitmap)
                }
                text(prompt)
            }
            var outputContent = ""
            generativeModel.generateContentStream(inputContent)
                .collect { response ->
                    outputContent += response.text
                    emit(Response.Success(outputContent))
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Response.Error(e.cause))
        }
    }
}