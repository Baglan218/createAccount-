package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.registration.StoreDatabase.COLUMN_EMAIL;
import static com.example.registration.StoreDatabase.COLUMN_GROUP;
import static com.example.registration.StoreDatabase.COLUMN_INFO;
import static com.example.registration.StoreDatabase.COLUMN_PASSWORD;
import static com.example.registration.StoreDatabase.COLUMN_PHONE;
import static com.example.registration.StoreDatabase.TABLE_USER;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_fullname, et_email, et_phone, et_group, et_password;
    Button btn_CreatAccount, btn_Login;

    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    public void initViews() {
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_phone = findViewById(R.id.et_phone);
        et_fullname = findViewById(R.id.et_fullname);
        et_group = findViewById(R.id.et_group);

        btn_CreatAccount = findViewById(R.id.btn_CreatAccount);
        btn_Login = findViewById(R.id.btn_Login);

        btn_CreatAccount.setOnClickListener(this);
        btn_Login.setOnClickListener(this);

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_CreatAccount:

                boolean createAccount = true;
                if(et_fullname.getText().toString().isEmpty()){
                    et_fullname.setError("Try again");
                    createAccount = false;
                }
                if(et_email.getText().toString().isEmpty()){
                    et_email.setError("Try again");
                    createAccount = false;
                }
                if(et_password.getText().toString().isEmpty()){
                    et_password.setError("Try again");
                    createAccount = false;
                }
                if(et_phone.getText().toString().isEmpty()){
                    et_phone.setError("Try again");
                    createAccount = false;
                }
                if(et_group.getText().toString().isEmpty()){
                    et_group.setError("Try again");
                    createAccount = false;
                }
                if (createAccount){
                    ContentValues userValues = new ContentValues();
                    userValues.put(COLUMN_INFO,et_fullname.getText().toString());
                    userValues.put(COLUMN_EMAIL,et_email.getText().toString());
                    userValues.put(COLUMN_PASSWORD,et_password.getText().toString());
                    userValues.put(COLUMN_GROUP,et_group.getText().toString());
                    userValues.put(COLUMN_PHONE,et_phone.getText().toString());

                    sqLiteDatabase.insert(TABLE_USER,null,userValues);

                    Toast.makeText(this,"Create account success!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Fill all info , try again!",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_Login:

                break;

        }
    }
}