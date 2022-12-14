package com.example.appquanlidiem.phat_tkb_database

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.view.View

class OneClickListener(private val listener: View.OnClickListener) : View.OnClickListener {
    private var lastClickTime: Long = 0
    private val timeInterval = 500;
    override fun onClick(v: View?) {
        if (System.currentTimeMillis() - lastClickTime > timeInterval) {
            listener.onClick(v)
        }
        lastClickTime = System.currentTimeMillis()

    }
}