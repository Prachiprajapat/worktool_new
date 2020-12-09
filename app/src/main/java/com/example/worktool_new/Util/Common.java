package com.example.worktool_new.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.Toast;
import com.example.worktool_new.R;
import java.util.ArrayList;
import java.util.HashSet;

public class Common {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ProgressDialog pd;

    public static boolean InternetCheck(Activity activity) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) activity.getSystemService("connectivity");
        NetworkInfo wifi = connectivityMgr.getNetworkInfo(1);
        NetworkInfo mobile = connectivityMgr.getNetworkInfo(0);
        if (wifi != null && wifi.isConnected()) {
            return true;
        }
        if (mobile != null) {
            return mobile.isConnected();
        }
        return false;
    }

    public static boolean InternetCheckFragment(Context fragment) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) fragment.getSystemService("connectivity");
        NetworkInfo wifi = connectivityMgr.getNetworkInfo(1);
        NetworkInfo mobile = connectivityMgr.getNetworkInfo(0);
        if (wifi != null && wifi.isConnected()) {
            return true;
        }
        if (mobile != null) {
            return mobile.isConnected();
        }
        return false;
    }

    public static void showToast(Activity activity, String message) {
        Toast.makeText(activity, message, 0).show();
    }

    public static void showToastFragment(Context context, String message) {
        Toast.makeText(context, message, 0).show();
    }

    public static ArrayList<String> getAllMedia(Activity activity, String[] projection, Uri uri, String data) {
        HashSet<String> videoItemHashSet = new HashSet<>();
        Cursor cursor = activity.getContentResolver().query(uri, projection, (String) null, (String[]) null, (String) null);
        try {
            cursor.moveToFirst();
            do {
                videoItemHashSet.add(cursor.getString(cursor.getColumnIndexOrThrow(data)));
            } while (cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(videoItemHashSet);
    }

    public static void showLoading(Activity activity, String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity, R.style.AppCompatAlertDialogStyle);
        pd = progressDialog;
        progressDialog.setMessage(message);
        pd.setCancelable(false);
        if (pd.isShowing()) {
            pd.dismiss();
        } else {
            pd.show();
        }
    }

    public static void dismiss() {
        ProgressDialog progressDialog = pd;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public static void showLoadingFragment(Context activity, String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity, R.style.AppCompatAlertDialogStyle);
        pd = progressDialog;
        progressDialog.setMessage(message);
        pd.setCancelable(false);
        if (pd.isShowing()) {
            pd.dismiss();
        } else {
            pd.show();
        }
    }

    public static void dismissFragment() {
        ProgressDialog progressDialog = pd;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
