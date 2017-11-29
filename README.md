# NetworkStateChangeBroadcastReceiver
```kotlin
class MainActivity : AppCompatActivity() {
    
    val intentFilter by lazy { IntentFilter() }
    val receiver by lazy { NetworkStateChangeReceiver() }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intentFilter.addAction(NetworkUtil.CONNECTIVITY_ACTION)
        
        receiver.setNetworkStateListener(object: NetworkStateChangeReceiver.NetworkStateChangedListener {
            override fun onChanged(connectivityStatusString: String, connectivityStatus: Int) {
                println("$connectivityStatusString : $connectivityStatus")
            }
    
            override fun onConnected() {
                println("onConnected")
            }
    
            override fun onDisconnected() {
                println("onDisconnected")
            }
        })
    }
    
    override fun onPostResume() {
        super.onPostResume()
        registerReceiver(receiver, intentFilter)
    }
    
    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
}
```

```xml
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

```gradle
dependencies {
	compile 'com.github.leonkamerlinn:NetworkStateChangeBroadcastReceiver:1.0'
}
```
