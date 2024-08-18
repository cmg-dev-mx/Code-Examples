package mx.dev.shellcore.android.broadcasttimer

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.Timer
import java.util.TimerTask

class StopWatchService: Service() {

    companion object {
        const val CURRENT_TIME = "current_time"
        const val UPDATED_TIME = "updated_time"
    }

    private val timer = Timer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time = intent.getDoubleExtra(CURRENT_TIME, 0.0)
        timer.schedule(StopWatchTimerClass(time), 0, 1000)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private inner class StopWatchTimerClass(private var time: Double): TimerTask() {

        override fun run() {
            val intent = Intent(UPDATED_TIME)
            intent.setPackage(packageName)
            time++
            intent.putExtra(CURRENT_TIME, time)
            sendBroadcast(intent)
        }

    }
}