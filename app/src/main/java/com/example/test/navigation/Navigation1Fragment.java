package com.example.test.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.test.R;

public class Navigation1Fragment extends Fragment  {


    private TextView tv_one;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation1, container, false);
        findId(view);
        return view;
    }


    private void findId(View view) {
        tv_one = (TextView) view.findViewById(R.id.tv_one);

        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation2Fragment,new Bundle());

            }
        });
    }


}
