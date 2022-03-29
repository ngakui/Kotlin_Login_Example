package fr.iim.firebaseauth.ui.welcome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val message = MutableLiveData<Any>()

    fun setMsgCommunicator(msg:String){
        message.value = msg
    }
}