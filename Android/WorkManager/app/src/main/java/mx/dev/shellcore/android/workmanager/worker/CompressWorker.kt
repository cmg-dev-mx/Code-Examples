package mx.dev.shellcore.android.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressWorker(
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {

    override fun doWork() = try {
        Log.i("Compress worker", "doWork: Compressing...")
        Thread.sleep(5000)
        Log.i("Compress worker", "doWork: Compress finished")
        Result.success()
    } catch (e: Exception) {
        Result.failure()
    }
}