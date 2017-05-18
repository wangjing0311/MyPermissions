package com.example.win.mypermissions;

import android.app.Activity;
import android.support.annotation.NonNull;

public abstract class BaseActivity extends Activity {

    private IPermissionsCallback permissionsCallback;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsCallback != null)
            PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this, new PermissionUtils.doSomethingResult() {
                @Override
                public void onResult(int permisionIndexCount) {
                    permissionsCallback.onResult(permisionIndexCount);
                }
            });
    }

    public void setPermissionsCallback(IPermissionsCallback permissionsCallback) {
        this.permissionsCallback = permissionsCallback;
    }

    public IPermissionsCallback getPermissionsCallback() {
        return permissionsCallback;
    }
}
