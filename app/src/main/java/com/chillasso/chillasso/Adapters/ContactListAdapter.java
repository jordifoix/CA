package com.chillasso.chillasso.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chillasso.chillasso.Class.Contact;
import com.chillasso.chillasso.R;

import org.w3c.dom.Text;

import java.util.List;

import static android.R.attr.resource;

/**
 * Created by Enric on 02/04/2017.
 */

public class ContactListAdapter extends ArrayAdapter<Contact> {
    private final List<Contact> contactList;

    public ContactListAdapter(@NonNull Context context, List<Contact> contactList) {
        super(context, R.layout.contact_list_item,contactList);
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.contact_list_item,parent,false);
        TextView name_textView = (TextView) view.findViewById(R.id.name_textView);
        TextView phonenumber_textView = (TextView) view.findViewById(R.id.phonenumber_textView);
        name_textView.setText(contactList.get(position).getName());
        phonenumber_textView.setText(contactList.get(position).getPhone());
        return view;

    }
}
