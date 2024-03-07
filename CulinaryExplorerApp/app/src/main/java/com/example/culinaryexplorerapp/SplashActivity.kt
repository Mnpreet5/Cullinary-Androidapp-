package com.example.culinaryexplorerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val getStartedButton: Button = findViewById(R.id.button)
        getStartedButton.setOnClickListener {
            // Handle the "Get Started" button click
            navigateToUserAuthenticationActivity()
        }
    }

    private fun navigateToUserAuthenticationActivity() {
        val intent = Intent(this, UserAuthenticationActivity::class.java)
        startActivity(intent)
        finish() // Optional: Close the splash activity so the user cannot go back
    }
}
