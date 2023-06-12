package com.mfarhan08a.hangoutyuk.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mfarhan08a.hangoutyuk.MainActivity
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.databinding.ActivityLoginBinding
import com.mfarhan08a.hangoutyuk.ui.register.RegisterActivity
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.ui.onboarding.OnboardingActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.getToken().observe(this@LoginActivity) {
            if (it != null) {
                navigateToMain()
            }
        }

        binding.apply {
            btnRegister.setOnClickListener {
                navigateToRegister()
            }
            btnLogin.setOnClickListener {
                login()
            }
            imageView.setOnClickListener {
                navigateToMain()
            }
        }
    }

    private fun login() {
        binding.apply {
            val email = edLoginEmail.text.toString().trim()
            val password = edLoginPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8) {
                    loginViewModel.login(email, password).observe(this@LoginActivity) {
                        when (it) {
                            is Result.Loading -> showLoading(true)
                            is Result.Success -> {
                                showLoading(false)
                                loginViewModel.getOnboarding()
                                    .observe(this@LoginActivity) { onboard ->
                                        if (onboard!!) {
                                            navigateToMain()
                                        } else {
                                            navigateToOnboarding()
                                        }
                                    }
                            }
                            is Result.Error -> {
                                showLoading(false)
                                Toast.makeText(
                                    this@LoginActivity,
                                    getString(R.string.login_failed),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity, getString(R.string.invalid_data), Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@LoginActivity, getString(R.string.enter_data), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun navigateToOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}