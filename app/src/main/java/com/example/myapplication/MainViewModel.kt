package com.example.myapplication

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel : ViewModel(), LifecycleObserver {
    private val _state: MutableLiveData<String> = MutableLiveData()
    val state: LiveData<String>
        get() = _state


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("ViewModel", "onCreate $this")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d("ViewModel", "onPause $this")
    }

    fun fetch(v: View) {
        viewModelScope.launch(Dispatchers.Default) {
            runBlocking {
                Thread.sleep(2000)
                _state.postValue("show")
                Thread.sleep(5000)
                _state.postValue("dismiss")
            }
        }
    }


    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }

}