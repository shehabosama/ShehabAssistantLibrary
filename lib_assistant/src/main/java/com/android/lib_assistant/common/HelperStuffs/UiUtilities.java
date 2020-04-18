package com.android.lib_assistant.common.HelperStuffs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by peter on 27/05/18.
 */

public class UiUtilities {

    public static void showBasicDialog(Context context, String message, String positiveString, String negativeString) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);

        builder1.setPositiveButton(
                positiveString,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                negativeString,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static void showActionDialog(Context context, String title, String message, String positiveString, String negativeString, DialogInterface.OnClickListener positiveClickListener, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setPositiveButton(
                positiveString,
                positiveClickListener);

        builder1.setNegativeButton(
                negativeString,
                negativeClickListener);

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void hideSoftKeyboard(Context context) {
        if (context != null) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) context.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && ((Activity) context).getWindow().getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(((Activity) context).getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }
}
