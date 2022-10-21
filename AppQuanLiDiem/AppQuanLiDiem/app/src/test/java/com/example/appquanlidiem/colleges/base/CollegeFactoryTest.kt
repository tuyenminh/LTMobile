package com.example.appquanlidiem.colleges_tkb.base

import junit.framework.TestCase

class CollegeFactoryTest : TestCase() {

    fun testGetCollegeNameList() {
        println(CollegeFactory.collegeNameList)
    }

    fun testCreateCollege() {
        assertNull(CollegeFactory.createCollege(null))
        assertNull(CollegeFactory.createCollege(""))

    }
}