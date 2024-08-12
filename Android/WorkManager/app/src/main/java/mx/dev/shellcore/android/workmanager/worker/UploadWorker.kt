package mx.dev.shellcore.android.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Locale

class UploadWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    companion object {
        const val KEY_DATA = "data"
        const val KEY_RESULT = "result"
    }

    override fun doWork() = try {

        val data = inputData.getString(KEY_DATA)

        Log.i("Upload worker", "doWork: Uploading... $data")
        Thread.sleep(3000)
        Log.i("Upload worker", "doWork: Upload finished")

        val time = SimpleDateFormat("HH:mm:ss", Locale.US).format(System.currentTimeMillis())

        val outputData = Data.Builder()
            .putString(KEY_RESULT, "Upload finished at $time")
            .build()

        Result.success(outputData)
    } catch (e: Exception) {
        Result.failure()
    }
}