package com.thanh_nguyen.baseproject.external

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sendbird.android.BaseChannel
import com.sendbird.android.BaseMessage
import com.sendbird.android.OpenChannel
import com.sendbird.android.SendBird
import com.thanh_nguyen.baseproject.common.Constants

class SendBirdSdkHelper {
    companion object{
        fun init(context: Context){
            SendBird.init(Constants.APP_ID, context)
            subscribeMessageReceived()
        }

        private var onMessageReceived = MutableLiveData<Pair<BaseChannel?, BaseMessage?>>()

        private fun subscribeMessageReceived(){
            Log.e("subscribe","OK")
            SendBird.addChannelHandler("1092109302193210", object: SendBird.ChannelHandler(){
                override fun onMessageReceived(p0: BaseChannel?, p1: BaseMessage?) {
                    Log.e("channel ${p0?.name}", "msg: ${Gson().toJson(p1)}")
                    onMessageReceived.postValue(Pair(p0, p1))
                }
            })
        }

    }

    private var currentOpenChannel: OpenChannel? = null
    fun connect(userId: String){
        // The USER_ID below should be unique to your Sendbird application.
        // The USER_ID below should be unique to your Sendbird application.
        SendBird.connect(userId) { user, e ->
            if (e != null) {
                Log.e("connect failed", "ERROR: ${e.message}")
                e.printStackTrace()
            }
            Log.e("connected", Gson().toJson(user))
            // The user is connected to Sendbird server.
        }
    }

    fun getOnMessageReceivedListener(): LiveData<Pair<BaseChannel?, BaseMessage?>> = onMessageReceived

    fun createNewChannel(){
        OpenChannel.createChannel{
            openChannel, exception ->
            if (exception != null) {
                Log.e("open channel", "Error")
                exception.printStackTrace()
            }
            Log.e("createNew success", Gson().toJson(openChannel))
        }
    }

    fun joinChannel(channelUrl: String){
        // The CHANNEL_URL below can be retrieved using the openChannel.getUrl().
        OpenChannel.getChannel(channelUrl) {
            openChannel, e ->
                if (e != null) {
                    // Handle error.
                    Log.e("joinChannel err", e.message?:"??")
                    e.printStackTrace()
                }

            openChannel.enter {
                if(e != null){
                    Log.e("enter error", "${e.message}")
                    e.printStackTrace()
                    return@enter
                }
                this.currentOpenChannel = openChannel
                Log.e("enter successful", "ok")
            }
                // Call the instance method of the result object in the "openChannel" parameter of the onResult() callback method.
        }
    }

    fun sendMessage(msg: String, isSuccess: (Boolean) -> Unit){
        if(currentOpenChannel == null)
            return

        currentOpenChannel?.sendUserMessage(msg){
            userMsg, exception ->
                if(exception != null){
                    Log.e("send msg err", "error")
                    exception.printStackTrace()
                    isSuccess.invoke(false)
                    return@sendUserMessage
                }
            Log.e("send success", "success: ${Gson().toJson(userMsg)}")
            isSuccess.invoke(true)
        }
    }
}