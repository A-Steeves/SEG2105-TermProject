package com.example.lab3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.edit_account);
        Button deleteAccountBtn = findViewById(R.id.deleteAccount);
        Intent thisIntent = this.getIntent();
        NewDBHandler thisDb = new NewDBHandler(AccountActivity.this);

        String userName = thisIntent.getStringExtra("userName");
        String accountType = thisIntent.getStringExtra("accountType");
        TextView thisUsername = findViewById(R.id.userName);
        TextView thisAccountType = findViewById(R.id.accountType);

        thisUsername.setText(userName);
        thisAccountType.setText(accountType);

        deleteAccountBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !accountType.equals( "Admin" ) ) {
                    thisDb.deleteAccount(userName);
                }
            }
        });
    }
    public void onBackPressed(){
        setResult(RESULT_OK);
        finish();
    }
}


//        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View r) {
//                TextView coName = findViewById(R.id.name);
//                TextView coCode = findViewById(R.id.textView7);
//                String newName = coName.getText().toString();
//                String newCode = coCode.getText().toString();
//                thisDb.editCourse( name, newCode, newName );
//            }
//        });

