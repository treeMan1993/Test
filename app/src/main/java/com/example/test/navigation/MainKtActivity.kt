package com.example.test.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.example.test.R

class MainKtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kt)
    }

    override fun onSupportNavigateUp() =
            findNavController(this, R.id.fragment_test).navigateUp()
}
