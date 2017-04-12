package com.chillasso.chillasso.TabFragments;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.chillasso.chillasso.Adapters.ContactListAdapter;
import com.chillasso.chillasso.Class.Contact;
import com.chillasso.chillasso.Class.UserPhone;
import com.chillasso.chillasso.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Enric on 04/04/2017.
 */

public class CreateGroupTab extends Fragment {

    private EditText groupName;
    private Button save_group_button;
    private ListView contactListview;
    private EditText search_contact_editText;
    private TextView group_members_textView;
    private boolean first;
    private ContactListAdapter contactListAdapter;
    private ContactListAdapter searchContactListAdapter;
    private List<UserPhone> users;
    private List<Contact> contacts;
    private List<Contact> search_contact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_group_tab,container,false);
        groupName = (EditText) view.findViewById(R.id.group_name_editText);
        save_group_button = (Button) view.findViewById(R.id.save_group_button);
        contactListview = (ListView) view.findViewById(R.id.contact_listView);
        search_contact_editText = (EditText) view.findViewById(R.id.search_editText);
        group_members_textView = (TextView) view.findViewById(R.id.group_members_textView);

        first = true;

        askPermissions();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        contacts = new ArrayList<Contact>();
        search_contact = new ArrayList<Contact>();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        users = realm.where(UserPhone.class).findAll();
        realm.commitTransaction();


        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor =getActivity().getApplicationContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, sortOrder);
        while (cursor.moveToNext()) {
            //fer un buscador
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
            if (phonenumber != null && name != null) {
                try {
                    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                    Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phonenumber, "");
                    int countryCode = numberProto.getCountryCode();
                    String nationalNumber = String.valueOf(numberProto.getNationalNumber());
                    if (searchContact(name, nationalNumber)) {

                    } else {
                        if (searchWithRealm(nationalNumber)) {
                            Contact contact = new Contact(name, nationalNumber);
                            contacts.add(contact);
                            Log.d("afegit","adsas:  "+nationalNumber);
                        }
                    }

                } catch (NumberParseException e) {
                    e.printStackTrace();
                }

            }
        }
        cursor.close();

        contactListAdapter = new ContactListAdapter(getActivity().getApplicationContext(),contacts);
        contactListview.setAdapter(contactListAdapter);

        search_contact_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search_contact.clear();
                for(Contact contact: contacts){
                    if(Search(s.toString(),contact)){
                        search_contact.add(contact);
                    }
                }
                searchContactListAdapter = new ContactListAdapter(getActivity().getApplicationContext(),search_contact);
                if(contactListview != null){
                    searchContactListAdapter.notifyDataSetChanged();
                    contactListview.setAdapter(searchContactListAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        save_group_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save group i guardar el contacte del user treientli el @mydomain.com
            }
        });

        contactListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(first){
                    if(search_contact.size() == 0) {
                        group_members_textView.setText(group_members_textView.getText().toString() + " You," + contacts.get(position).getName());
                    }
                    else{
                        group_members_textView.setText(group_members_textView.getText().toString()+" You,"+search_contact.get(position).getName());
                    }
                    first = false;
                }
                else {
                    if(search_contact.size() == 0)
                        group_members_textView.setText(group_members_textView.getText().toString()+","+contacts.get(position).getName());
                    else
                        group_members_textView.setText(group_members_textView.getText().toString()+","+search_contact.get(position).getName());
                }
            }
        });
        return view;

    }
    private boolean Search(String s, Contact contact) {
        for(int i = 0; i < s.length();++i){
            if( s.charAt(i) != contact.getName().charAt(i) && upsideDown(s.charAt(i)) != contact.getName().charAt(i)
                    && s.charAt(i) != contact.getPhone().charAt(i)) return false;
        }
        return true;
    }

    private char upsideDown(char c) {
        if (c >= 65 && c <= 90) return (char) (c+32);
        return (char) (c-32);
    }

    private boolean searchWithRealm(String phonenumber) {
        for (UserPhone phone : users) {
            if (phone.getPhone().equals(phonenumber)) return true;
        }
        return false;

    }

    private boolean searchContact(String name, String phone) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name) || contact.getPhone().equals(phone)) return true;
        }
        return false;
    }

    private void askPermissions() {
        if (ContextCompat.checkSelfPermission( getActivity().getApplicationContext(),
                android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)

        {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}
