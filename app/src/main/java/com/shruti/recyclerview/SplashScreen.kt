package com.shruti.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shruti.recyclerview.databinding.ActivitySplashScreenBinding
import com.shrutix.recyclerview.MainActivity

class SplashScreen : AppCompatActivity() {
    lateinit var binding : ActivitySplashScreenBinding//declare binding//
    lateinit var splashScreenBinding: ActivitySplashScreenBinding //declare splash screen//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater) //initialize binding//
        setContentView(binding.root)
        Handler().postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        },2000)// initialize splash screen using handler (handle splash screen)//
    }
}