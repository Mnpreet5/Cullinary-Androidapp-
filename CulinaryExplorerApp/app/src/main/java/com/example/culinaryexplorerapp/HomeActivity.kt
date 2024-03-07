package com.example.culinaryexplorerapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val searchView = findViewById<SearchView>(R.id.searchView)
        val imageView1 = findViewById<ImageView>(R.id.imageView8)
        val imageView2 = findViewById<ImageView>(R.id.imageView9)
        val imageView3 = findViewById<ImageView>(R.id.imageView10)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Implement search functionality here
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Implement search functionality here
                return true
            }
        })

        imageView1.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DetailsActivity::class.java))
        }


        imageView2.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DetailsActivity::class.java))
        }


        imageView3.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DetailsActivity::class.java))
        }


        val breakfastTextView = findViewById<TextView>(R.id.breakfastTextView)
        val lunchTextView = findViewById<TextView>(R.id.lunchTextView)
        val dinnerTextView = findViewById<TextView>(R.id.dinnerTextView)
        val dessertTextView = findViewById<TextView>(R.id.dessertTextView)
        val fastFoodTextView = findViewById<TextView>(R.id.fastFoodTextView)

        breakfastTextView.setOnClickListener {
            // Handle click on breakfastTextView
        }

        lunchTextView.setOnClickListener {
            // Handle click on lunchTextView
        }

        dinnerTextView.setOnClickListener {
            // Handle click on dinnerTextView
        }

        dessertTextView.setOnClickListener {
            // Handle click on dessertTextView
        }

        fastFoodTextView.setOnClickListener {
            // Handle click on fastFoodTextView
        }
    }

    private fun navigateToDetailsActivity() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }
}
