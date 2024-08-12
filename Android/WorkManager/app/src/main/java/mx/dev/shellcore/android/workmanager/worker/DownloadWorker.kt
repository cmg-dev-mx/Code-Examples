package mx.dev.shellcore.android.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadWorker(
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {

    override fun doWork() = try {
        Log.i("Download worker", "doWork: Downloading...")
        Thread.sleep(1500)
        Log.i("Download worker", "doWork: Download finished")
        Result.success()
    } catch (e: Exception) {
        Result.failure()
    }
}