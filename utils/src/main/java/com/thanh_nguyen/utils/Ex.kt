package com.thanh_nguyen.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import java.text.DecimalFormat


fun Context.copyToClipboard(label: String = "copied text", content: String) {
    val clipboardManager = ContextCompat.getSystemService(this, ClipboardManager::class.java)!!
    val clip = ClipData.newPlainText(label, content)
    clipboardManager.setPrimaryClip(clip)
}

fun Context.hasPermissions(vararg permissions: String) = permissions.all { permission ->
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}


fun View.onClick(f: () -> Unit){
    setOnClickListener {
        f.invoke()
    }
}

fun Activity.showMessage(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}


fun Any?.isNull() = this == null

fun Double.formatPrice(pattern: String? = "###,###,###.00"): String = DecimalFormat(pattern).format(this)
