package com.nickolay.kotlin_for_android.extensions

import android.content.Context

inline fun Context.dip(value: Int) = resources.displayMetrics.density * value