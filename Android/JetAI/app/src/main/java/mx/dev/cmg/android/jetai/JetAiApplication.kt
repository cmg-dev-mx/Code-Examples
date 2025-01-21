package mx.dev.cmg.android.jetai

import android.app.Application

class JetAiApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}