package com.thanh_nguyen.baseproject.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.util.Log

class SmsReceiver: BroadcastReceiver() {
    companion object {
        val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
    }
    private val smsManager = SmsManager.getDefault()
    private val pdu_type = "pdus"
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("have a meessage come", "ok")
        val pdus = intent?.extras?.get(pdu_type) as Array<*>?

        if (pdus == null || pdus.isEmpty())
            return

        val messages = arrayOfNulls<SmsMessage>(pdus.size)
        val sb = StringBuilder()
        for (i in pdus.indices) {
            messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray?)
            sb.append(messages[i]?.messageBody)
            Log.e("body", "${messages[i]?.messageBody}")
        }
        val sender = messages[0]?.originatingAddress
        val message = sb.toString()
    }
}