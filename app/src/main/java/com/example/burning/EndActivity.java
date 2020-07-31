package com.example.burning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.burning.DatabaseV2.DatabaseHelper;

import java.text.DateFormat;
import java.util.Calendar;

public class EndActivity extends AppCompatActivity {

    String startTime,endTime,dystans,duration,aktywnosc,weight;
    Float distancee;
    int kalorie,jule,typ;

    DatabaseHelper db;

    public static final String PREFS_NAME = "MySettingsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        hideNavigation();

        Intent intent = getIntent();
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("endTime");
        dystans = intent.getStringExtra("disc");
        duration = intent.getStringExtra("chronometerTime");
        weight = intent.getStringExtra("weight");

        TextView tv_start = findViewById(R.id.tv_startTime);
        tv_start.setText(startTime);

        TextView tv_end = findViewById(R.id.tv_endTime);
        tv_end.setText(endTime);

        float dist = Float.valueOf(dystans);
        distancee = dist / 1000;
        TextView tv_dystans = findViewById(R.id.tv_dystans);
        tv_dystans.setText(String.format("%.3f", distancee) + "Km");

        TextView tv_chronometer = findViewById(R.id.tv_chronometer);
        tv_chronometer.setText(duration);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        typ = prefs.getInt("Typ", 0);

        TextView tv_aktywnosc = findViewById(R.id.tv_aktywnosc);
        tv_aktywnosc.setText(typ + "");

        jule = (int) ((int) Integer.parseInt(weight) * distancee);

        kalorie = (int) (jule / 4.186);

        TextView tv_kalorie = findViewById(R.id.tv_kalorie);
        tv_kalorie.setText(kalorie+"");

        if (typ == 2) {
            aktywnosc = "Cycling";
            jule = (int) ((int) Integer.parseInt(weight) * distancee);

            kalorie = (int) ((jule / 4.186)/1.8);
        }
        else if (typ == 1){
            aktywnosc = "March";
            jule = (int) ((int) Integer.parseInt(weight) * distancee);

            kalorie = (int) (jule / 4.186);
        }
        else if (typ == 3){
            aktywnosc = "Running";
            jule = (int) ((int) Integer.parseInt(weight) * distancee);

            kalorie = (int) (jule / 4.186);
        }

    }

    public void addItem(View view) {

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());


        db = new DatabaseHelper(this);
        boolean insert = db.insertinhistory(null,currentDate,startTime,endTime,duration,distancee,kalorie,aktywnosc);
        if(insert==true){
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigation();
    }

    public void hideNavigation(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    public void btnBack(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

}
