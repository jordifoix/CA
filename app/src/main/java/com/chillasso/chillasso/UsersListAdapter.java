package com.chillasso.chillasso;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chillasso.chillasso.Class.UserRegistration;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.resource;

/**
 * Created by Enric on 31/03/2017.
 */

public class UsersListAdapter extends ArrayAdapter<UserRegistration> {

    private List<UserRegistration> users_list;

    public UsersListAdapter(@NonNull Context context,List<UserRegistration> users_list) {
        super(context, R.layout.users_list_item,users_list);
        this.users_list = users_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.users_list_item,parent,false);
        TextView user_textView = (TextView) view.findViewById(R.id.user_textView);
        user_textView.setText(users_list.get(position).getPhoneNumber());
        return view;
    }
}
