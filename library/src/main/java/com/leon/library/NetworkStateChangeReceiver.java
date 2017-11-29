package com.leon.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/***********************************************
 * Created by anartzmugika on 22/6/16.
 */

public class NetworkStateChangeReceiver extends BroadcastReceiver {
    private NetworkStateChangedListener mNetworkStateChangeListener = null;

    public void setNetworkStateListener(NetworkStateChangedListener networkStateListener) {
        mNetworkStateChangeListener = networkStateListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String connectivityStatusString = NetworkUtil.getConnectivityStatusString(context);
        int connectivityStatus = NetworkUtil.getConnectivityStatus(context);
        if (mNetworkStateChangeListener != null) {
            mNetworkStateChangeListener.onChanged(connectivityStatusString, connectivityStatus);
            if (connectivityStatus == NetworkUtil.TYPE_NOT_CONNECTED) {
                mNetworkStateChangeListener.onDisconnected();
            } else {
                mNetworkStateChangeListener.onConnected();
            }
        }
    }

    public interface NetworkStateChangedListener {
        void onChanged(@NonNull String connectivityStatusString, @NonNull int connectivityStatus);
        void onConnected();
        void onDisconnected();
    }
}
