package com.example.dailytv.fragments.fragment_play;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailytv.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeFragment extends Fragment{

    public LifeFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_yangshi, container, false);
    }
}
