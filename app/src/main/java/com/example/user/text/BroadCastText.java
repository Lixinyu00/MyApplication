package com.example.user.text;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * @author LiXinyu
 * @date 2017/9/8 10:36.
 */

public class BroadCastText extends Activity {

    private IntentFilter intentFilter;
    private NetworkChangeRecevier networkChangeRecevier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeRecevier = new NetworkChangeRecevier();
        registerReceiver(networkChangeRecevier, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeRecevier);
    }

    private class NetworkChangeRecevier extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                Toast.makeText(context, "network  is  available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context,"network  is  unavailable",Toast.LENGTH_LONG).show();
            }
        }
    }
}
