package com.example.culinaryexplorerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.culinaryexplorerapp.databinding.UserAuthenticationBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class UserAuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: UserAuthenticationBinding
    private lateinit var callbackManager: CallbackManager
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Facebook SDK
        callbackManager = CallbackManager.Factory.create()

        // Initialize Google sign-in options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Set OnClickListener for the Facebook ImageView
        binding.imageView5.setOnClickListener {
            // Initiate Facebook login process
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
        }

        // Set OnClickListener for the Google ImageView
        binding.imageView6.setOnClickListener {
            // Initiate Google sign-in process
            googleSignIn()
        }

        // Set OnClickListener for the email/password sign-up button
        binding.signupButton.setOnClickListener {
            signUpUser()
        }

        // Set OnClickListener for the email/password sign-in button
        binding.signinButton.setOnClickListener {
            logInUser()
        }

        // Callback registration for Facebook login
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    startActivity(Intent(this@UserAuthenticationActivity, HomeActivity::class.java))
                // Optional: Finish the current activity to prevent returning to it on back press
                }

                override fun onCancel() {
                    // Handle login cancellation
                    Toast.makeText(this@UserAuthenticationActivity, "Login Cancelled", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException) {
                    // Handle login error
                    Toast.makeText(this@UserAuthenticationActivity, "Login Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, authenticate with Firebase
            firebaseAuthWithGoogle(account?.idToken!!)
        } catch (e: ApiException) {
            // Sign in failed, handle failure
            Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = FirebaseAuth.getInstance().currentUser
                    Toast.makeText(this@UserAuthenticationActivity, "Google Sign-in Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@UserAuthenticationActivity, HomeActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("GoogleSignIn", "Google sign-in failed", task.exception)
                    Toast.makeText(this@UserAuthenticationActivity, "Google Sign-in Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun signUpUser() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@UserAuthenticationActivity, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@UserAuthenticationActivity, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this@UserAuthenticationActivity, "Please enter email and password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logInUser() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@UserAuthenticationActivity, "Sign In Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@UserAuthenticationActivity, HomeActivity::class.java))
                    } else {

                        Toast.makeText(this@UserAuthenticationActivity, "Sign In Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {

            Toast.makeText(this@UserAuthenticationActivity, "Please enter email and password", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}
