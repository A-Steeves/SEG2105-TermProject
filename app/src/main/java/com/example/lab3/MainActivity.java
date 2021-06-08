package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

    MyDBHandler dbHandler;
    Button btnView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new MyDBHandler(this);

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        priceBox = (EditText) findViewById(R.id.productPrice);

        btnView = (Button)findViewById(R.id.btnView);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });

    }

    public void newProduct (View view){
        MyDBHandler dbHandler = new MyDBHandler(this);


        double price = Double.parseDouble(priceBox.getText().toString());

        Product product = new Product(productBox.getText().toString(), price);

        dbHandler.addProduct(product);

        productBox.setText("");
        priceBox.setText("");



    }

    public void lookupProduct (View view){
        MyDBHandler dbHandler = new MyDBHandler(this);

        Product product = dbHandler.findProduct(productBox.getText().toString());

        if(product != null){
            idView.setText(String.valueOf(product.getID()));
            priceBox.setText(String.valueOf(product.getPrice()));

        } else{
            idView.setText("No Match Found");
        }
    }

    public void removeProduct (View view){
        MyDBHandler dbHandler = new MyDBHandler(this);

        boolean result = dbHandler.deleteProduct(productBox.getText().toString());

        if(result){
            idView.setText("Record Deleted");
            productBox.setText("");
            priceBox.setText("");
        } else {
            idView.setText("No Match Found");
        }
    }

    public void listView(){
        MyDBHandler dbHandler = new MyDBHandler(this);

    }


}