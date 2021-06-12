package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText priceBox;

    private EditText eName;
    private EditText ePassword;

    MyDBHandler dbHandler;
    private Button btnLogin;



    adminCredentials adminDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        adminDetails = new adminCredentials();

        dbHandler = new MyDBHandler(this);

        eName = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName = eName.getText().toString();
                String inputPass = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter a username and password.", Toast.LENGTH_SHORT).show();
                }
                else if(AdminValidation(inputName, inputPass)){
                    Toast.makeText(getApplicationContext(), "Hello Admin", Toast.LENGTH_SHORT).show();

                    openAdmin();

                }
                else if(ValidUserName(inputName)){
                    Toast.makeText(getApplicationContext(), "Valid Username.", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Your username or password is incorrect.", Toast.LENGTH_SHORT).show();
                }


            }


            });
    }

    private boolean AdminValidation(String name, String password){
        if(name.equals(adminDetails.getUsername()) && password.equals(adminDetails.getPassword())){
            return true;
        }
        return false;
    }
    private boolean ValidUserName(String username){
        return (!TextUtils.isEmpty(username) && Patterns.EMAIL_ADDRESS.matcher(username).matches());
    }



    private void openAdmin(){
        Intent intent = new Intent (this, AdminscreenActivity.class);
        startActivity(intent);
    }
}