package com.rishi.memology

import android.content.Intent
import android.net.sip.SipErrorCode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.sip.SipErrorCode.TIME_OUT
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_memepage.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//waitingrp()


        val intent = Intent(this, memepage::class.java)

        val handler = Handler()
             progressBar.visibility= View.VISIBLE
        handler.postDelayed(
            {
//                textChanger.setText("After some delay, it changed to new text") },
//            5000

                startActivity(intent)
                progressBar.visibility=View.GONE
    },2000)





}}