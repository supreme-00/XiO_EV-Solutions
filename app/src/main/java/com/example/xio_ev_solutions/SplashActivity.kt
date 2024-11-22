package com.example.xio_ev_solutions

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.Animation
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {
    lateinit var xioanimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val xioImageView = findViewById<ImageView>(R.id.xio_imageView)
        xioImageView.setBackgroundResource(R.drawable.xio_animation_list)
        xioanimation = xioImageView.background as AnimationDrawable
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            xioanimation.start()
        } else {
            xioanimation.stop()
        }
    }

    override fun onAnimationStart(animation: Animation?) {
        // Optional: Add behavior here if needed
    }

    override fun onAnimationEnd(animation: Animation?) {
        // Transition to MainActivity
        Intent(this, RegisterActivity::class.java).also {
            startActivity(it)
            finish() // Optional: Close SplashActivity
        }
    }

    override fun onAnimationRepeat(animation: Animation?) {
        // Optional: Add behavior here if needed
    }
}
