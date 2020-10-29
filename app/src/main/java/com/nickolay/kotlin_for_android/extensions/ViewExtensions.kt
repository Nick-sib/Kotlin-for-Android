package com.nickolay.kotlin_for_android.extensions

import android.view.View

inline fun View.dip(value: Int): Float = context.dip(value)