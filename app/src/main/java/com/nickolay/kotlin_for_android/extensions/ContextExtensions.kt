package com.nickolay.kotlin_for_android.extensions

import android.content.Context

inline fun Context.dip(value: Int): Float = resources.displayMetrics.density * value