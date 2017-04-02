package com.chillasso.chillasso.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chillasso.chillasso.Class.Contact;
import com.chillasso.chillasso.R;
import com.chillasso.chillasso.Class.UserPhone;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class CreateGroup extends AppCompatActivity {

    private List<UserPhone> users;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        askPermissions();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        contacts = new ArrayList<Contact>();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        users = realm.where(UserPhone.class).findAll();
        realm.commitTransaction();


        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, sortOrder);
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
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)

        {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}
