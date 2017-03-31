package com.chillasso.chillasso.TabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chillasso.chillasso.R;

/**
 * Created by Enric on 31/03/2017.
 */

public class NewHangoutTab extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_hangouts_tab,container,false);
        return view;
    }
}
