package com.example.appquanlidiem.util_tkb

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogUtils {

    @JvmStatic
    fun showTipDialog(context: Context, tip: String) {
        showTipDialog(context, "Thông báo", tip)
    }

    @JvmStatic
    fun showTipDialog(context: Context, title: String, tip: String) {
        AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(tip)
                .setPositiveButton("hiểu", null)
                .create()
                .show()
    }
}