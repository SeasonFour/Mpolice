package com.example.ibra.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Objects;

public class Report extends AppCompatActivity {
    EditText etViolationOther,etNumberPlate, etDescription,etLocation,etTime;
    String violationType;
    ParseObject obj;
    String Violation,Number_Plate, Description, Location, Time;
    RadioGroup rGroup;

    ParseUser currentUser;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Report a violation");
        setSupportActionBar(toolbar);

        etViolationOther = (EditText) findViewById(R.id.violationOther);
        etNumberPlate = (EditText) findViewById(R.id.numberPlate);
        etDescription = (EditText) findViewById(R.id.description);
        etLocation = (EditText) findViewById(R.id.location);
        etTime = (EditText) findViewById(R.id.time);
        rGroup = (RadioGroup) findViewById(R.id.radio);

        currentUser = ParseUser.getCurrentUser();
        Log.d("Current user", "" + currentUser);

    }

    public void typeOfViolation(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()){
            case R.id.accidentBtn:
            if (checked)
                Violation = "Accident";
                break;

            case R.id.hitrunBtn:
                if (checked)
                    Violation = "Hit and run";
                    break;

            case R.id.stolenBtn:
                if (checked)
                    Violation ="Stolen";
                    break;

        }
    }

    public void reportViolationClick(View v){
        //Violation = etViolationOther.getText().toString().trim();
        Number_Plate = etNumberPlate.getText().toString().trim();
        Description = etDescription.getText().toString();
        Location = etLocation.getText().toString();
        Time = etTime.getText().toString();

        if (Violation.equals(null)){
            Toast.makeText(getApplicationContext(),"Cant be null" ,Toast.LENGTH_SHORT).show();
        }else{
            new reportViolation().execute();
        }


    }

    public class reportViolation extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Report.this);
            pDialog.setMessage("Reporting a violation...");
            pDialog.setIndeterminate(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            obj = new ParseObject("Violations");
            obj.put("Violation", Violation);
            obj.put("Number_plate", Number_Plate);
            obj.put("Description", Description);
            obj.put("Location", Location);
            obj.put("Time", Time);
            obj.saveEventually();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            obj.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getBaseContext(),
                                "Reporting violation successful",
                                Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getBaseContext(),
                                "Error.Reporting violation up not successful",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

            pDialog.dismiss();
            /*etViolationOther.setText("");
            etNumberPlate.setText("");
            etDescription.setText("");
            etLocation.setText("");
            etTime.setText("");*/

        }

    }


}
