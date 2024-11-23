package com.example.xio_ev_solutions

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException

class RegisterActivity : AppCompatActivity() {

    // Request code for Google Sign In
    private val RC_GOOGLE_SIGN_IN = 1001

    // Facebook Callback Manager
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Edge-to-edge insets handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Facebook SDK (Important to call before setContentView or usage)
        FacebookSdk.sdkInitialize(applicationContext)
        callbackManager = CallbackManager.Factory.create()

        // Find all views
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvLoginNow = findViewById<TextView>(R.id.tvLoginNow)
        val btnFacebook = findViewById<ImageView>(R.id.btnFacebook)
        val btnGoogle = findViewById<ImageView>(R.id.btnGoogle)

        // Handle Sign up button click
        btnSignUp.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val phoneNumber = etPhoneNumber.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Basic validation
            if (fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Proceed with your sign-up logic (e.g., Firebase Auth or custom server)
            Toast.makeText(this, "Sign up success (demo)!", Toast.LENGTH_SHORT).show()

            // Example: Navigate to next screen
            Intent(this, PasswordActivity::class.java).also {
                startActivity(it)
            }
        }

        // Forgot Password (sample click)
        tvForgotPassword.setOnClickListener {
            // Navigate or show a dialog
            Toast.makeText(this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }

        // Already have an account? Login Now
        tvLoginNow.setOnClickListener {
            // Example: Navigate to LoginActivity
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

        // Facebook sign in
        btnFacebook.setOnClickListener {
            facebookLogin()
        }

        // Google sign in
        btnGoogle.setOnClickListener {
            googleSignIn()
        }
    }

    // -------------- Google Sign-In Logic --------------
    private fun googleSignIn() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.default_web_client_id))
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the GoogleSignInApi
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                // Signed in successfully
                val googleEmail = account?.email
                Toast.makeText(this, "Google Sign-In successful: $googleEmail", Toast.LENGTH_SHORT).show()

                // TODO: Use account info to authenticate with your backend or Firebase
            } catch (e: ApiException) {
                Log.e("RegisterActivity", "Google Sign-In failed", e)
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // -------------- Facebook Login Logic --------------
    private fun facebookLogin() {
        // Configure the permissions you need:
        LoginManager.getInstance().logInWithReadPermissions(
            this,
            listOf("email", "public_profile")
        )

        // Register callback
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    Toast.makeText(this@RegisterActivity, "Facebook Login Success", Toast.LENGTH_SHORT).show()
                    // result.accessToken -> you can use this token
                }

                override fun onCancel() {
                    Toast.makeText(this@RegisterActivity, "Facebook Login Canceled", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(this@RegisterActivity, "Facebook Login Error", Toast.LENGTH_SHORT).show()
                    Log.e("RegisterActivity", "Facebook Login Error", error)
                }
            }
        )
    }
}
