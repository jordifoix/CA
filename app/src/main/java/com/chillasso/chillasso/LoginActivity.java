package com.chillasso.chillasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText phone_number_editText,password_editText;
    private CheckBox remember_me_checkBox;
    private Button sign_up_button,sign_in_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone_number_editText = (EditText) findViewById(R.id.phone_number_editText);
        password_editText = (EditText) findViewById(R.id.password_editText);
        remember_me_checkBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        sign_up_button = (Button) findViewById(R.id.sign_up_button);
        sign_in_button = (Button) findViewById(R.id.sign_in_button);

    }
}
