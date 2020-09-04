package com.nickolay.kotlin_for_android.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nickolay.kotlin_for_android.R

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        viewModel.viewData.observe(this, { tvMessage.text = it })
//
//        bClicker.setOnClickListener { viewModel.doClick() }
    }



}