package com.android.lib_assistant.common.HelperStuffs;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.android.lib_assistant.common.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by shehab on 28/11/2019.
 */

public abstract class PermissionHandlerFragment extends BaseFragment {

    private PermissionsListener permissionsListener = (PermissionsListener) this;
    private static final int MY_PERMISSIONS_REQUEST = 1000;

    public void checkPermissions(Context context, String... permissions) {
        ArrayList<String> notGrantedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                notGrantedPermissions.add(permission);
            }
        }
        if (notGrantedPermissions.size() == 0) {
            permissionsListener.onPermissionGranted(permissions);
        } else {
            requestPermissions(notGrantedPermissions.toArray(new String[notGrantedPermissions.size()]), MY_PERMISSIONS_REQUEST);
        }
    }

    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                boolean permissionsGranted = true;
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            permissionsGranted = false;
                            break;
                        }
                    }
                }
                if (permissionsGranted)
                    permissionsListener.onPermissionGranted(permissions);
                else
                    permissionsListener.onPermissionDenied(permissions);
            }
            break;
        }
    }
}