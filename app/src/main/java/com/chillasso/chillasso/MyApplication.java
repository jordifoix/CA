package com.chillasso.chillasso;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.chillasso.chillasso.Activities.LoginActivity;
import com.chillasso.chillasso.Activities.MainActivity;
import com.chillasso.chillasso.Class.UserPhone;
import com.chillasso.chillasso.Class.UserRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Enric on 30/03/2017.
 */

public class MyApplication extends Application {

    private UserRegistration currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        //Login
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<UserRegistration> users = realm.where(UserRegistration.class).findAll();
        if (users.size()>0) {
            currentUser = users.get(users.size()-1);
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(currentUser.getPhoneNumber()+"@mydomain.com",currentUser.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(MyApplication.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        }
        else {
            Intent intent = new Intent(MyApplication.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        realm.commitTransaction();

        //fi logino

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                realm.beginTransaction();
                for(DataSnapshot user : dataSnapshot.getChildren()){
                    String phone = user.getKey();
                    UserPhone userPhone = new UserPhone(phone);
                    realm.copyToRealmOrUpdate(userPhone);
                }
                realm.commitTransaction();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            }


}
