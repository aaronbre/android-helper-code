package com.example.aaronbrecher.androidhelpers.UiUtils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager


/**
 * This function will determine the amount of columns with the specified width that can
 * fit on the device screen. To be used with RecyclerView GridLayout and span amount
 */
private fun numberOfColumnsForGrid(windowManager: WindowManager, gridWidth: Int): Int {
    val displayMetrics = DisplayMetrics()
    windowManager.getDefaultDisplay().getMetrics(displayMetrics)
    // You can change this divider to adjust the size of the poster
    val width = displayMetrics.widthPixels
    return width / gridWidth
}