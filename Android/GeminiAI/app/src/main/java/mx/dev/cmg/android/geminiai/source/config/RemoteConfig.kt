package mx.dev.cmg.android.geminiai.source.config

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import mx.dev.cmg.android.geminiai.R

private val configSettings = remoteConfigSettings {
    minimumFetchIntervalInSeconds = 3600  // 1 hour
}

val remoteConfig by lazy {
    Firebase.remoteConfig
        .apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_config_defaults)
            fetchAndActivate()
        }
}