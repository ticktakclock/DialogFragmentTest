package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val loadingDialog: MyDialogFragment = MyDialogFragment.newInstance()
    private var isResumed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate $this")
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            Handler().postDelayed({
                Log.d("TAG", "post delayed 1000 $loadingDialog")
                Log.d("TAG", "fragmentmanager $supportFragmentManager")
                loadingDialog.show(supportFragmentManager, null)
            }
                , 1000
            )

            Handler().postDelayed(
                {
                    Log.d("TAG", "post delayed 3000 $loadingDialog")
                    Log.d("TAG", "activity $this")
                    Log.d("TAG", "fragmentmanager $supportFragmentManager")
                    if(supportFragmentManager.isDestroyed.not()) {
                        loadingDialog.dismiss()
                    } else{
                        // TODO: なんとかして消す
                    }
                }, 10000
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume")
        isResumed = true
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause")
        isResumed = false
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy")

    }
}
