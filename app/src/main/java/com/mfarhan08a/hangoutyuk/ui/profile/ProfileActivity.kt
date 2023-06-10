package com.mfarhan08a.hangoutyuk.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.data.model.DataUser
import com.mfarhan08a.hangoutyuk.databinding.ActivityProfileBinding
import com.mfarhan08a.hangoutyuk.ui.login.LoginActivity
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory
import com.mfarhan08a.hangoutyuk.util.reduceFileImage
import com.mfarhan08a.hangoutyuk.util.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(application)
    }

    private var getFile: File? = null
    private var userData: DataUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }

        userData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_USER, DataUser::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_USER)
        }
        setProfileData(userData!!)

        profileViewModel.apply {
            getId().observe(this@ProfileActivity) { id ->
                getToken().observe(this@ProfileActivity) { token ->
                    binding.btnSave.setOnClickListener {
                        val isName = binding.edName.text.toString() != userData?.name.toString()
                        val isEmail =
                            binding.edEmail.text.toString() != userData?.email.toString() &&
                                    Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.text.toString())
                                        .matches()
                        val isPassword =
                            !binding.edPassword.text.isNullOrEmpty() && binding.edPassword.text.toString().length >= 8
                        val isPhoto = getFile != null

                        Log.d(TAG, "name: $isName, email: $isEmail, password: $isPassword")

                        if (isName || isEmail || isPassword || isPhoto) {
                            Toast.makeText(
                                this@ProfileActivity,
                                getString(R.string.loading),
                                Toast.LENGTH_SHORT
                            ).show()
                            if (isPhoto) {
                                postPhoto(token!!, id!!)
                            }
                            if (isName || isEmail || isPassword)
                                saveNewProfile(token!!, id!!)
                        } else {
                            Toast.makeText(
                                this@ProfileActivity,
                                getString(R.string.cannot_change_profile),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    binding.btnDelete.setOnClickListener {
                        deleteAccount(token, id)
                    }
                }
            }
        }

        binding.btnAddPhoto.setOnClickListener {
            startGallery()
        }

    }

    private fun deleteAccount(token: String?, id: String?) {
        profileViewModel.apply {
            deleteUser(token!!, id!!).observe(this@ProfileActivity) {
                when (it) {
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        Toast.makeText(
                            this@ProfileActivity,
                            getString(R.string.account_deleted),
                            Toast.LENGTH_SHORT
                        ).show()
                        clearToken()
                        navigateToLogin()
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            this@ProfileActivity,
                            it.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveNewProfile(token: String, id: String) {
        val name = if (binding.edName.text.toString() != userData?.name) {
            binding.edName.text.toString()
        } else {
            null
        }
        val email = if (binding.edEmail.text.toString() != userData?.email.toString()) {
            binding.edEmail.text.toString()
        } else {
            null
        }
        val password = if (!binding.edPassword.text.isNullOrEmpty()) {
            binding.edPassword.text.toString()
        } else {
            null
        }

        profileViewModel.updateProfile(token, id, name, email, password).observe(this) {
            when (it) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    Toast.makeText(
                        this,
                        getString(R.string.post_succed),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(
                        this,
                        it.error,
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }

    }

    private fun postPhoto(token: String, id: String) {
        showLoading(true)
        if (token != null) {
            val file = reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photoFile",
                file.name,
                requestImageFile
            )
            Log.d(TAG, "file: $imageMultipart")
            profileViewModel.postPhotoProfile(token, id, imageMultipart).observe(this) {
                when (it) {
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        Toast.makeText(
                            this,
                            getString(R.string.post_succed),
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            this,
                            getString(R.string.post_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, getString(R.string.choose_picture))
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@ProfileActivity)
            getFile = myFile
            binding.ivPhotoProfile.setImageURI(selectedImg)
            Log.d(TAG, "file: $getFile")

        }
    }

    private fun setProfileData(data: DataUser) {
        val name = data.name
        val email = data.email
        if (data.photoUrl == null) {
            binding.ivPhotoProfile.setImageResource(R.drawable.blank_picture)
        } else {
            Glide.with(this@ProfileActivity)
                .load(data.photoUrl)
                .into(binding.ivPhotoProfile)
        }
        binding.apply {
            edName.setText(name)
            edEmail.setText(email)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        private val TAG = ProfileActivity::class.java.simpleName
    }
}