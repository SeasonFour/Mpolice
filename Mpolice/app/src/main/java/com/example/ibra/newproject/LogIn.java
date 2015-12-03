package com.example.ibra.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LogIn extends AppCompatActivity implements View.OnClickListener{
    @InjectView(R.id.badge_no)
    EditText et_badge;

    @InjectView(R.id.pin_no)
    EditText et_pin;

    @InjectView(R.id.login)
    Button login;

    @InjectView(R.id.request_pin)
    TextView request;

    String badgeNumber;

    List<ParseObject> obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.inject(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        badgeNumber = et_badge.getText().toString();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("BadgeNumber",badgeNumber );
        try {
            obj = query.find();
            Intent i = new Intent(LogIn.this,MainActivity.class);
            startActivity(i);
        }catch (ParseException e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Invalid badge number", Toast.LENGTH_SHORT);
        }
        
    }
}
