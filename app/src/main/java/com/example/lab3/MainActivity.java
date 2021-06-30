package com.example.lab3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {


    NewDBHandler dB;
    private final String studentType = "Student";
    private final String instructorType = "Instructor";
    private final String adminType = "Admin";


    private EditText eName;
    private EditText ePassword;

    Spinner userType;
    private Button btnSignup, btnLogin;

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
        //testPage();
        setContentView(R.layout.activity_login);

        adminDetails = new adminCredentials();

        dB = new NewDBHandler(this);
        userType = findViewById(R.id.userType);

        eName = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        btnSignup = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnlogin);

        String[] types = new String[]{"Admin", "Student", "Instructor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        userType.setAdapter(adapter);

        btnSignup.setOnClickListener(new View.OnClickListener() {
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

                else if(ValidUserName(inputName) && validatePassword(inputPass)){
                    if(usertype.equals(adminType)){
                        Toast.makeText(getApplicationContext(), "User Type cannot be admin.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(!dB.findAccount(inputName,inputPass)) {
                            dB.addAccount(inputName, inputPass, usertype);
                            Toast.makeText(getApplicationContext(), "Welcome new user.", Toast.LENGTH_SHORT).show();
                            openUser(inputName, inputPass, usertype);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "User already Exists.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                //Once
                else {
                    if(!ValidUserName(inputName)){
                        Toast.makeText(getApplicationContext(), "Username incorrect must be valid email.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Password is too weak. ", Toast.LENGTH_SHORT).show();
                        passAlert();
                    }
                }
            }


            });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eName.getText().toString();
                String inputPass = ePassword.getText().toString();
                String usertype = userType.getSelectedItem().toString();



                if(AdminValidation(inputName, inputPass) && usertype.equals(adminType)){
                    Toast.makeText(getApplicationContext(), "Hello Admin", Toast.LENGTH_SHORT).show();
                    openAdmin();
                } else if(inputName.isEmpty() || inputPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Credentials are empty.", Toast.LENGTH_SHORT).show();
                } else if(dB.findAccount(inputName, inputPass)){
                    Toast.makeText(getApplicationContext(), "Welcome back "+inputName, Toast.LENGTH_SHORT).show();
                    openUser(inputName, inputPass, usertype);
                } else{
                    Toast.makeText(getApplicationContext(), "User does not exist. ", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent (this, MainActivity2.class);
        startActivity(intent);
    }
    private boolean validatePassword(String pass){

        if(!PASSWORD_PATTERN.matcher(pass).matches()){
            return false;

        }else{
            return true;
        }
    }
    private void openUser(String name, String pass, String type){
        Intent intent = new Intent (this, welcome_screen.class);
        intent.putExtra("name", name);

        intent.putExtra("type", type);

        startActivity(intent);
    }
    String m_Text = "";
    private void passAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle();

        // Set up the input
        builder.setMessage("A password must have 1 upper case and 1 lower case letter, 1 number and at least 6 characters long.");

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void testPage(){
        Intent intent = new Intent(this, IntructorCoursePage.class);
        startActivity(intent);

    }


}