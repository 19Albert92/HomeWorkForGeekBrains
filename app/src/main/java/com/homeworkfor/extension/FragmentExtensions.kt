package com.homeworkfor.extension

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.homeworkfor.R
import java.util.*

fun Fragment.snackBar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_INDEFINITE).show()
}

fun Fragment.showWebWiki(text: String) {
    startActivity(Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(text)
    })
}

fun Fragment.getDaysAgo(daysAgo: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
    return calendar.time
}