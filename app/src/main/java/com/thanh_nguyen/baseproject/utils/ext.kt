package com.thanh_nguyen.baseproject.utils

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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.common.base.adapter.RecycleViewHolder
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseViewModel
import java.text.DecimalFormat
import java.util.*

fun Any.toJson(): String{
    return Gson().toJson(this)
}

fun Context.hasPermissions(vararg permissions: String) = permissions.all { permission ->
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.copyToClipboard(label: String = "copied text", content: String) {
    val clipboardManager = ContextCompat.getSystemService(this, ClipboardManager::class.java)!!
    val clip = ClipData.newPlainText(label, content)
    clipboardManager.setPrimaryClip(clip)
}

fun Date.plusDays(days: Int): Date {
    return Date(this.time + (days * 24 * 60 * 60 * 1000))
}

fun Date.plusMillis(millis: Long): Date {
    return Date(this.time + millis)
}

fun Any?.isNull() = this == null

fun Double.formatPrice(pattern: String? = "###,###,###.00"): String = DecimalFormat(pattern).format(this)

fun <VB: ViewDataBinding> inflateDataBinding(parent: ViewGroup, @LayoutRes layoutRes: Int, attachParent: Boolean = false): VB{
    return DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        layoutRes,
        parent,
        attachParent
    )
}

inline fun <reified T: RecyclerView.ViewHolder> ViewGroup.createViewHolder(
    @LayoutRes layoutRes: Int,
    attachParent: Boolean? = false,
    createVH: (View) -> T): T{
    return createVH(LayoutInflater
        .from(context)
        .inflate(layoutRes, this, attachParent?:false)
    )
}

fun View.onClick(f: () -> Unit){
    setOnClickListener {
        f.invoke()
    }
}

fun Activity.showMessage(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

inline fun <reified T, reified LD: LiveData<T>> Fragment.observeLiveDataChanged(liveData: LD, crossinline onChanged: (T) -> Unit){
    liveData.observe(viewLifecycleOwner, onChanged)
}

inline fun <reified T, reified LD: LiveData<T>> AppCompatActivity.observeLiveDataChanged(liveData: LD, observer: Observer<in T>){
    liveData.observe(this, observer)
}