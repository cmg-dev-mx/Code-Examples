package mx.dev.shellcore.android.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class FilterWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork() = try {
        Log.i("Filter worker", "doWork: Filtering...")
        Thread.sleep(2000)
        Log.i("Filter worker", "doWork: Filter finished")
        Result.success()
    } catch (e: Exception) {
        Result.failure()
    }
}