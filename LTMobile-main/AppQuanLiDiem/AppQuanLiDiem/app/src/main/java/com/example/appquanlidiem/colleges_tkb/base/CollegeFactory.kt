package com.example.appquanlidiem.colleges_tkb.base

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.text.TextUtils


import java.util.*
import kotlin.collections.HashMap

object CollegeFactory {
    private val collegeMap = HashMap<String, Class<out College>>()



    @JvmStatic
    val collegeNameList: List<String> = ArrayList(collegeMap.keys)
    init {
        collegeNameList.sorted()
    }


    @JvmStatic
    fun createCollege(collegeName: String?): College? {
        if(TextUtils.isEmpty(collegeName)){
            return null;
        }
        return collegeMap[collegeName]?.newInstance();
    }


}