package com.leon.library

import android.content.Context

/**
 * Created by Leon on 29.11.2017..
 */
fun Context.haveInternetConnection(): Boolean = NetworkUtil.isConnected(this)