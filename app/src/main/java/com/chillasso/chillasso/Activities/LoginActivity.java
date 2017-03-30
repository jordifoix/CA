package com.chillasso.chillasso.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.chillasso.chillasso.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText phone_number_editText,password_editText;
    private CheckBox remember_me_checkBox;
    private Button sign_up_button,sign_in_button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone_number_editText = (EditText) findViewById(R.id.phone_number_editText);
        password_editText = (EditText) findViewById(R.id.password_editText);
        remember_me_checkBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        sign_up_button = (Button) findViewById(R.id.sign_up_button);
        sign_in_button = (Button) findViewById(R.id.sign_in_button);
        mAuth = FirebaseAuth.getInstance();
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phone_number_editText.getText().toString();
                String password = password_editText.getText().toString();
                if(TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"All field musts be filled",Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(phoneNumber,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumber = phone_number_editText.getText().toString()+"@mydomain.com";
                final String password = password_editText.getText().toString();
                if(TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"All field musts be filled",Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(phoneNumber, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(LoginActivity.this,"Sign up succesful!!",Toast.LENGTH_SHORT).show();
                            mAuth.signInWithEmailAndPassword(phoneNumber,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }
            }
        });

    }
}