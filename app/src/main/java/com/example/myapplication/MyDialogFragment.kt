package com.example.myapplication

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)
        builder.apply {
            setMessage("message")
        }
        // Create the AlertDialog object and return it
        return builder.create()
    }

    companion object {

        fun newInstance(): MyDialogFragment {
            return MyDialogFragment()
        }
    }

}