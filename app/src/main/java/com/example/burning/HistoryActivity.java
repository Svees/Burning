package com.example.burning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.burning.DatabaseV2.ItemAdapter;
import com.example.burning.DatabaseV2.DatabaseHelper;
import com.example.burning.DatabaseV2.HistoryData;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    DatabaseHelper db;
    private Spinner spinner;
    private static final String[] paths = {"All", "Walking", "Running", "Cycling", "Last week", "Last month"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        db = new DatabaseHelper(this);

        pokazAll();
    }

    public void pokazAll(){
        List<HistoryData> list = new ArrayList<>();


        Cursor date = db.alldata();
        while(date.moveToNext()) {
            String data = date.getString(1);
            String startTime = date.getString(2);
            String endTime = date.getString(3);
            String duration = date.getString(4);
            Float distance = date.getFloat(5);
            int calorie = date.getInt(6);
            String aktywnosc = date.getString(7);

            HistoryData h = new HistoryData(data,startTime,endTime,duration,distance,calorie,aktywnosc);
            list.add(h);


        }
        ItemAdapter adapter = new ItemAdapter(list,this);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}