package com.example.culinaryexplorerapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Initialize views
        val dishNameTextView = findViewById<TextView>(R.id.dishNameTextView)
        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        val ingredientsTextView = findViewById<TextView>(R.id.ingredientsTextView)
        val instructionsTextView = findViewById<TextView>(R.id.instructionsTextView)

        // Set data
        dishNameTextView.text = "RED SAUCE PASTA"
        val details = "Difficulty: Medium\nCooking Time: 30 mins\nCalories: 250\nRating: 4.5"
        detailsTextView.text = details
        val ingredients = """
            Ingredients:
            ▢ 8 ounces uncooked pasta
            ▢ 2 tablespoons butter
            ▢ 2-3 cloves garlic, minced
            ▢ 2 tablespoons tomato paste
            ▢ 1 (14 ounce) can tomato sauce
            ▢ 3/4 cup heavy/whipping cream
            ▢ 1/4 teaspoon Italian seasoning
            ▢ Salt and pepper to taste
            ▢ Freshly grated parmesan cheese for serving, to taste
            """.trimIndent()
        ingredientsTextView.text = ingredients
        val instructions = """
            Instructions:
            1. Boil a large, salted pot of water for your pasta and cook it al dente according to package instructions.
            2. Meanwhile (once the pasta starts to cook), add the butter to a skillet over medium-high heat. Once it melts, add the garlic and sauté for about a minute, stirring constantly.
            3. Add in the tomato paste, tomato sauce, cream, and Italian seasoning. Stir until it's nice and smooth. Let the sauce cook for about 5 minutes or until it's thickened to your liking (it should be very gently bubbling, so you may need to turn down the heat).
            4. Season the sauce with salt and pepper as needed. If the sauce is a little too acidic/tangy for your liking, add in a pinch of sugar.
            5. Drain the pasta and toss with the sauce (add a splash of hot pasta water if the sauce gets too thick). Serve immediately with plenty of parmesan cheese over top.
            """.trimIndent()
        instructionsTextView.text = instructions
    }
}

