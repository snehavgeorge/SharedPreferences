package com.example.cm.sharedpreferences;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    SharedPreferences sharedpreferences;
    TextView name;
    TextView email;
    Button save,retrieve,clear;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    ProgressDialog pd;
    RelativeLayout relativelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativelayout = (RelativeLayout)findViewById(R.id.relativelayout);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("loading");


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }

        save = (Button)findViewById(R.id.save);
        retrieve = (Button)findViewById(R.id.retrieve);
        clear = (Button)findViewById(R.id.clear);




    }


    public void Save(View view) {
        pd.show();

        String n = name.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.commit();

        pd.dismiss();
        Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();

    }


    public void clear(View view) {
        pd.show();
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        name.setText("");
        email.setText("");
        pd.dismiss();


        Snackbar snackbar = Snackbar
                .make(relativelayout, "Successfully cleared", Snackbar.LENGTH_LONG);
        snackbar.show();

    }
    public void Get(View view) {
        pd.show();
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }

        pd.dismiss();

        Snackbar snackbar = Snackbar
                .make(relativelayout, "Successfully retrieved", Snackbar.LENGTH_LONG);
        snackbar.show();
    }



}
