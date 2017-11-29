package com.leon.library;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by anartzmugika on 22/6/16.
 */

public class NetworkUtil {

    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 0;


    public static final String CONNECT_TO_WIFI = "WIFI";
    public static final String CONNECT_TO_MOBILE = "MOBILE";
    public static final String CONNECT_TO_MOBILE_2G = "2G";
    public static final String CONNECT_TO_MOBILE_3G = "3G";
    public static final String CONNECT_TO_MOBILE_4G = "4G";
    public static final String CONNECT_TO_UNDEFINED = "UNDEFINED";
    public static final String NOT_CONNECT = "NOT_CONNECT";
    public static final String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;

        }
        return TYPE_NOT_CONNECTED;
    }

    public static boolean isConnected(Context context) {
        return getConnectivityStatus(context) != TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info==null || !info.isConnected())
            return NOT_CONNECT; //not connected
        if(info.getType() == ConnectivityManager.TYPE_WIFI)
            return CONNECT_TO_WIFI;
        if(info.getType() == ConnectivityManager.TYPE_MOBILE){
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return CONNECT_TO_MOBILE_2G;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return CONNECT_TO_MOBILE_3G;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return CONNECT_TO_MOBILE_4G;
                default:
                    return CONNECT_TO_UNDEFINED;
            }
        }
        return CONNECT_TO_UNDEFINED;
    }
}
