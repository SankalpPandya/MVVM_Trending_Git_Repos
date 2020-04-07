package com.example.olaassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Util {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Test for connection
        NetworkCapabilities mCapabilities = mConnectivityManager.getNetworkCapabilities(mConnectivityManager.getActiveNetwork());
        return mCapabilities != null &&
                (mCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        mCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
    }
}
