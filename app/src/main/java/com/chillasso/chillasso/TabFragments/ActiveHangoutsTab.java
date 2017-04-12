package com.chillasso.chillasso.TabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.chillasso.chillasso.R;

/**
 * Created by Enric on 31/03/2017.
 */

public class ActiveHangoutsTab extends Fragment{

    private EditText search_hangout_editText;
    private ListView hangouts_listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.active_hangouts_tab,container,false);



        return view;
    }
}
