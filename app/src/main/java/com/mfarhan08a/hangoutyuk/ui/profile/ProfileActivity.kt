package com.mfarhan08a.hangoutyuk.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.data.model.DataRegister
import com.mfarhan08a.hangoutyuk.databinding.ActivityProfileBinding
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }

        profileViewModel.apply {
            getId().observe(this@ProfileActivity) { id ->
                getToken().observe(this@ProfileActivity) { token ->
                    profileViewModel.getUserById(token!!, id!!).observe(this@ProfileActivity) {
                        when (it) {
                            is Result.Success -> {
                                setProfileData(it.data.data)
                                showLoading(false)
                            }
                            is Result.Error -> {
                                showLoading(false)
                            }
                            is Result.Loading -> {
                                showLoading(true)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setProfileData(data: DataRegister) {
        var name = data.name
        var email = data.email
        binding.apply {
            edName.setText(name)
            edEmail.setText(email)
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}