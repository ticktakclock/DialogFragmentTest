package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val loadingDialog: MyDialogFragment by lazy{ MyDialogFragment.newInstance()}
    private var isResumed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            Handler().postDelayed({
                Log.d("TAG", "post delayed 1000")
                if (isResumed) loadingDialog.show(supportFragmentManager, null)
            }
                , 1000
            )
            Handler().postDelayed(
                {
                    Log.d("TAG", "post delayed 3000")
                    if(supportFragmentManager.isDestroyed.not()) {
                        loadingDialog.dismiss()
                    } else{
                        // TODO: なんとかして消す
                    }
//                    loadingDialog.dismissAllowingStateLoss()
                }, 3000
            )
        }
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
