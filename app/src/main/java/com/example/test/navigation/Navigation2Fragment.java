package com.example.test.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;

public class Navigation2Fragment extends Fragment {

    private TextView tv_two;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation2, container, false);
        findId(view);
        return view;
    }

    private void findId(View view) {
        tv_two = (TextView) view.findViewById(R.id.tv_two);

        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigationFragment);

            }
        });
    }


}
