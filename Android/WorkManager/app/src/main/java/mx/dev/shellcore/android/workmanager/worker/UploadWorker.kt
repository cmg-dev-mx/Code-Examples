package mx.dev.shellcore.android.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork() = try {
        Log.i("Upload worker", "doWork: Uploading...")
        Thread.sleep(3000)
        Log.i("Upload worker", "doWork: Upload finished")
        Result.success()
    } catch (e: Exception) {
        Result.failure()
    }
}