package com.example.ibra.newproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ViolationsQuery extends AppCompatActivity {
    RecyclerView recyclerV;
    LinearLayoutManager layoutManager;
    List<ParseObject> obj;
    List<String> number_plate = new ArrayList<String>();
    List<String> description = new ArrayList<String>();
    List<String> owner = new ArrayList<String>();
    List<String> status = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mpolice_recycler);

        recyclerV = (RecyclerView) findViewById(R.id.recycler_violations);
        layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerV.setLayoutManager(layoutManager);
        recyclerV.setHasFixedSize(true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Violations");
        try {
            obj = query.find();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i =0; i<obj.size();i++){
            number_plate.add(obj.get(i).getString("NumberPlate"));
            description.add(obj.get(i).getString("Description"));
            owner.add(obj.get(i).getString("Owner"));
            status.add(obj.get(i).getString("Status"));
        }


        recyclerV.setAdapter(new MpoliceAdapter(getApplicationContext(),number_plate,description,owner,status));

    }

    //public class getViolations()
}
