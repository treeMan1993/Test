package com.example.test.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.test.R;

public class NavigationMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_test);
        NavHostFragment.findNavController(fragment).getCurrentDestination();
        return NavHostFragment.findNavController(fragment).navigateUp();
    }

}
