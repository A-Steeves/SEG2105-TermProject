package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.ArrayAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class AdminFragment2 extends Fragment {

    private ArrayList<Account> accountList;
    ListView listView;
    Button findBtn, showAllBtn, deleteBtn;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView( @NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState ) {

        View v = inflater.inflate(R.layout.fragment_admin2, container, false ); // creates the view

        findBtn = v.findViewById( R.id.Find ); // assigns the Btns
        showAllBtn = v.findViewById( R.id.AllUser );
        listView = v.findViewById( R.id.SHOWLIST );
        NewDBHandler thisDb = new NewDBHandler(getActivity()); // creates instances of NewDBHandler
        accountList = new ArrayList<Account>();// creates new ArrayList of Accounts
//        Account testAcc = new Account("Alex@uottawa.ca", "Alex123", "Student" );
//        accountList.add( testAcc );
        accountList = thisDb.allAccounts(); // copies all the Accounts in DB into accountList
//        String[] display = {"Ajay", "Manvir", "Alex"};

        AccountListAdapter thisAdapter = new AccountListAdapter(getActivity(), R.layout.account_adapter_view_layout, accountList);

        listView.setAdapter(thisAdapter);
        listView.setClickable(true);
//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, display);
//        listView.setAdapter(listViewAdapter);

//        ActivityResultLauncher<Intent> activityClosed = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        accountList.clear();
//                        accountList.addAll(thisDb.allAccounts());
//                        thisAdapter.notifyDataSetChanged();
//                    }
//                }
//        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> adapter, View view, int position, long id ) {

                Account thisAccount = accountList.get(position);
                String userName = thisAccount.getUsername();
                String accountType = thisAccount.getAccountType();

                Intent intent = new Intent(getActivity(), AccountActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("accountType", accountType);

//                based on item add info to intent
                startActivity(intent);
//                activityClosed.launch(intent);
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                TextView userNameIn = v.findViewById( R.id.thisUsername );
                String userName = userNameIn.getText().toString();
                Account found = thisDb.findAccountUsername( userName );
                if ( found != null ) {
                    accountList.clear();
                    accountList.add( found );
                    thisAdapter.notifyDataSetChanged();
                }
            }
        });

        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                accountList.clear();
                accountList.addAll( thisDb.allAccounts() );
                thisAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }

}


//package com.example.lab3;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//public class AdminFragment2 extends Fragment {
//    ListView listView;
//    @Nullable
//    @org.jetbrains.annotations.Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_admin2,container,false);
//        listView = v.findViewById(R.id.listviewUser);
//
//        String[] display = {"Ajay", "Manvir", "Alex"};
//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,display);
//        listView.setAdapter(listViewAdapter);
//        //listView.setAdapter(adapter);
//        return v;
//    }
//
//   /* private void setAdapter() {
//        recyclerAdapter adapter = new recyclerAdapter(courseList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext());
//        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//    }*/
//}