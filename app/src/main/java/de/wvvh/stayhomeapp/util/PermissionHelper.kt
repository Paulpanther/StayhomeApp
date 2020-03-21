package de.wvvh.stayhomeapp.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHelper {

    fun hasPermissions(c: Context, permissions: Array<String>): Boolean {
        return permissions.all { permission ->
            ContextCompat.checkSelfPermission(c, permission) == PackageManager.PERMISSION_GRANTED }
    }

    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int = 0) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }
}