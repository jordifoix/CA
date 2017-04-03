package com.chillasso.chillasso.TabFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chillasso.chillasso.Activities.LoginActivity;
import com.chillasso.chillasso.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Enric on 31/03/2017.
 */

public class SettingsTab extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_tab,container,false);
        Button erase_account_button = (Button) view.findViewById(R.id.erase_account_button);
        Button log_out_button = (Button) view.findViewById(R.id.log_out_button);
        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Intent intent = new Intent((getActivity()).getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
