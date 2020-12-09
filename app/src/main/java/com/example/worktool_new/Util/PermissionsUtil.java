package com.example.worktool_new.Util;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionsUtil {
    public static String ACCOUNTS = "android.permission.GET_ACCOUNTS";
    public static String CAMERA = "android.permission.CAMERA";
    public static String LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static String READ_CALENDAR = "android.permission.READ_CALENDAR";
    public static String READ_CONTACTS = "android.permission.READ_CONTACTS";
    public static String READ_SMS = "android.permission.READ_SMS";
    public static String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    public static String STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    public static String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";
    public static String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    static String accountsPermErrorMsg = "Contacts permission denied. You can enable permission from settings";
    static String accountsPermInfoMsg = "App needs this permission to access Google Account on phone. Are you sure you want to deny this permission ?";
    static String calendarPermErrorMsg = "Calendar permission denied. You can enable permission from settings";
    static String calendarPermInfoMsg = "App needs this permission to access phone's calendar. Are you sure you want to deny this permission ?";
    static String cameraPermErrorMsg = "Camera permission denied. You can enable permission from settings";
    static String cameraPermInfoMsg = "App needs this permission to capture photos using phone's camera. Are you sure you want to deny this permission ?";
    static String contactsPermErrorMsg = "Contacts permission denied. You can enable permission from settings";
    static String contactsPermInfoMsg = "App needs this permission to access phone contacts. Are you sure you want to deny this permission ?";
    static Activity context;
    static String locationPermErrorMsg = "Location permission denied. You can enable permission from settings";
    static String locationPermInfoMsg = "App needs this permission to access your location. Are you sure you want to deny this permission ?";
    static String permission;
    static PermissionListener permissionListener;
    static int requestCode = 1001;
    static String smsPermErrorMsg = "SMS permission denied. You can enable permission from settings";
    static String smsPermInfoMsg = "App needs this permission to receive/read SMS for auto detection of OTP. Are you sure you want to deny this permission ?";
    static String storagePermErrorMsg = "Storage permission denied. You can enable permission from settings";
    static String storagePermInfoMsg = "App needs this permission to store files on phone's storage. Are you sure you want to deny this permission ?";
    static String tag;

    public interface PermissionListener {
        void onPermissionResult(boolean z);
    }

    public static void askPermissions(Activity context2, String permission1, String permission2, PermissionListener permissionListener2) {
        context = context2;
        permissionListener = permissionListener2;
        if (ContextCompat.checkSelfPermission(context2, permission1) == 0 && ContextCompat.checkSelfPermission(context2, permission2) == 0) {
            permissionListener2.onPermissionResult(true);
            return;
        }
        ActivityCompat.requestPermissions(context2, new String[]{permission1, permission2}, requestCode);
    }

    public static void onRequestPermissionsResult(final int requestCode2, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == 0) {
            permissionListener.onPermissionResult(true);
        } else if (grantResults.length > 1 && grantResults[0] == 0 && grantResults[1] == 0) {
            permissionListener.onPermissionResult(true);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            alertBuilder.setCancelable(true);
            alertBuilder.setTitle((CharSequence) "Permission Required");
            if (permission.equals(STORAGE)) {
                alertBuilder.setMessage((CharSequence) storagePermInfoMsg);
            } else if (permission.equals(READ_SMS)) {
                alertBuilder.setMessage((CharSequence) smsPermInfoMsg);
            } else if (permission.equals(RECEIVE_SMS)) {
                alertBuilder.setMessage((CharSequence) smsPermInfoMsg);
            } else if (permission.equals(ACCOUNTS)) {
                alertBuilder.setMessage((CharSequence) accountsPermInfoMsg);
            } else if (permission.equals(READ_CALENDAR)) {
                alertBuilder.setMessage((CharSequence) calendarPermInfoMsg);
            } else if (permission.equals(WRITE_CALENDAR)) {
                alertBuilder.setMessage((CharSequence) calendarPermInfoMsg);
            } else if (permission.equals(CAMERA)) {
                alertBuilder.setMessage((CharSequence) cameraPermInfoMsg);
            } else if (permission.equals(LOCATION)) {
                alertBuilder.setMessage((CharSequence) locationPermInfoMsg);
            } else if (permission.equals(READ_CONTACTS)) {
                alertBuilder.setMessage((CharSequence) contactsPermInfoMsg);
            } else if (permission.equals(WRITE_CONTACTS)) {
                alertBuilder.setMessage((CharSequence) contactsPermInfoMsg);
            }
            alertBuilder.setPositiveButton((CharSequence) "Grant", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(PermissionsUtil.context, new String[]{PermissionsUtil.permission}, requestCode2);
                }
            });
            alertBuilder.setNegativeButton((CharSequence) "Deny", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    PermissionsUtil.permissionListener.onPermissionResult(false);
                }
            });
            alertBuilder.create().show();
        } else {
            if (permission.equals(STORAGE)) {
                Toast.makeText(context, storagePermErrorMsg, 1).show();
            } else if (permission.equals(READ_SMS)) {
                Toast.makeText(context, smsPermErrorMsg, 1).show();
            } else if (permission.equals(RECEIVE_SMS)) {
                Toast.makeText(context, smsPermErrorMsg, 1).show();
            } else if (permission.equals(ACCOUNTS)) {
                Toast.makeText(context, accountsPermErrorMsg, 1).show();
            } else if (permission.equals(READ_CALENDAR)) {
                Toast.makeText(context, calendarPermErrorMsg, 1).show();
            } else if (permission.equals(WRITE_CALENDAR)) {
                Toast.makeText(context, calendarPermErrorMsg, 1).show();
            } else if (permission.equals(CAMERA)) {
                Toast.makeText(context, cameraPermErrorMsg, 1).show();
            } else if (permission.equals(LOCATION)) {
                Toast.makeText(context, locationPermErrorMsg, 1).show();
            } else if (permission.equals(READ_CONTACTS)) {
                Toast.makeText(context, contactsPermErrorMsg, 1).show();
            } else if (permission.equals(WRITE_CONTACTS)) {
                Toast.makeText(context, contactsPermErrorMsg, 1).show();
            }
            permissionListener.onPermissionResult(false);
        }
    }
}
