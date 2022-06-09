package com.homeworkfor.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.homeworkfor.R

fun AppCompatActivity.showFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        add(R.id.fragment_container, fragment, fragment::class.simpleName.toString())
        commit()
    }
}