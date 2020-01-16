package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("TAG", "onCreateDialog $this")
        return AlertDialog.Builder(context!!)
            .apply {
                setMessage("message")
            }
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Fragment", "onAttach  $this $fragmentManager")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Fragment", "onDetach $this $fragmentManager")
//        dismiss()
    }

    override fun dismiss() {
        Log.d("TAG", "dissmiss $this $fragmentManager")
        super.dismiss()
    }

    companion object {

        fun newInstance(): MyDialogFragment {
            Log.d("Fragment", "newInstance")
            return MyDialogFragment()
        }
    }

}