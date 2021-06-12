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
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private final String studentType = "Student";
    private final String instructorType = "Instructor";
    private final String adminType = "Admin";


    private EditText eName;
    private EditText ePassword;

    Spinner userType;
    private Button btnLogin;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +   // one digit
                    "(?=.*[a-z])"+    // one lower case letter
                    "(?=.*[A-Z])"+    // one upper case
                    "(?=\\S+$)"+      // no white spaces
                    ".{6,}"+          //6 characters
                    "$");





    adminCredentials adminDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        adminDetails = new adminCredentials();


        userType = findViewById(R.id.userType);

        eName = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnlogin);


        String[] types = new String[]{"Admin", "Student", "Instructor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        userType.setAdapter(adapter);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName = eName.getText().toString();
                String inputPass = ePassword.getText().toString();
                String usertype = userType.getSelectedItem().toString();

                //checks to see if credentials are empty.
                if(inputName.isEmpty() || inputPass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Credentials are empty.", Toast.LENGTH_SHORT).show();
                }
                    //Checks to see if admin credentials are correct and if they selected type admin.
                else if(AdminValidation(inputName, inputPass) || usertype.equals(adminType)){
                    Toast.makeText(getApplicationContext(), "Hello Admin", Toast.LENGTH_SHORT).show();

                    openAdmin();
                }
                else if(!ValidUserName(inputName) || !validatePassword(inputPass)){

                    //Checks to see if the username or password entered was too weak and releases a toast telling use that.
                    if(!ValidUserName(inputName)){
                        Toast.makeText(getApplicationContext(), "Username incorrect.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Password is too weak.", Toast.LENGTH_SHORT).show();
                    }

                }
                //Once
                else {
                    if (usertype.equals(studentType)) {
                        //This is a student class.
                    } else {
                        //This is an instructor class.
                    }
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
    private boolean validatePassword(String pass){

        if(!PASSWORD_PATTERN.matcher(pass).matches()){
            return false;

        }else{
            return true;
        }
    }
}