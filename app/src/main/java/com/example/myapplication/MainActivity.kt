package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val loadingDialog: MyDialogFragment = MyDialogFragment.newInstance()
    private var isResumed = false

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private fun obtainViewModel() =
        ViewModelProviders.of(this, MainViewModel.Factory())
            .get(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate $this")
        setContentView(R.layout.activity_main)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = obtainViewModel()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.button.text = "gga"
        lifecycle.addObserver(viewModel)
        subscribe()


//        button.setOnClickListener {
//            Handler().postDelayed({
//                Log.d("TAG", "post delayed 1000 $loadingDialog")
//                Log.d("TAG", "fragmentmanager $supportFragmentManager")
//                loadingDialog.show(supportFragmentManager, null)
//            }
//                , 1000
//            )
//
//            Handler().postDelayed(
//                {
//                    Log.d("TAG", "post delayed 3000 $loadingDialog")
//                    Log.d("TAG", "activity $this")
//                    Log.d("TAG", "fragmentmanager $supportFragmentManager")
//                    if(supportFragmentManager.isDestroyed.not()) {
//                        loadingDialog.dismiss()
//                    } else{
//                        // TODO: なんとかして消す
//                    }
//                }, 10000
//            )
//        }
    }

    fun subscribe() {
        viewModel.state.observe(this, Observer { data ->
            Log.d("TAG", "observer $data $this $supportFragmentManager")
            when (data) {
                "show" -> loadingDialog.show(supportFragmentManager, null)
                "dismiss" -> loadingDialog.dismiss()
            }
        })
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
        loadingDialog.dismiss()
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
