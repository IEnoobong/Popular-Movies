/*
 * Copyright (c) 2017. Ibanga Enoobong Ime (Entrepreneur, Developer Marathoner)
 *
 */

package co.enoobong.popularmovies.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class Utility {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public static AlertDialog.Builder showDialog(Context context, int icon, int message) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setIcon(icon);
    }

    public static void showToast(Context context, String message, int length) {
        Toast.makeText(context, message, length).show();
    }
}
