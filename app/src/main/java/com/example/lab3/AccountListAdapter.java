package com.example.lab3;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class AccountListAdapter extends ArrayAdapter<Account> {

    private Context accountContext;
    private int accountResource;

    public AccountListAdapter( Context context, int resource, ArrayList<Account> thisAccountList ){
        super( context, resource, thisAccountList );
        accountContext = context;
        accountResource = resource;
    }

    public View getView( int pos, View thisView, ViewGroup parentGroup ){

        String userName = getItem( pos ).getUsername();
        String accountType = getItem( pos ).getAccountType();

        LayoutInflater inflater = LayoutInflater.from( accountContext );
        thisView = inflater.inflate( accountResource, parentGroup, false );

        TextView thisUsername = thisView.findViewById( R.id.accountUsername );
        TextView thisAccountType = thisView.findViewById( R.id.thisAccountType );

        thisUsername.setText( userName );
        thisAccountType.setText( accountType );

        return thisView;

    }
}
