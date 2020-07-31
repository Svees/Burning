package com.example.burning;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static com.example.burning.MapsActivity.PREFS_NAME;

public class ActivityFragment extends Fragment {

    int typ = 0;

    public static final String PREFS_NAME = "MySettingsFile";

    private Spinner spinner;
    private static final String[] paths = {"Walking", "Running", "Cycling"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// Walking
                        Toast toast = Toast.makeText(getContext(), "Walking", Toast.LENGTH_SHORT);
                        toast.show();

                        typ = 1;

                        hideNavigation();

                        break;
                    case 1: // Running
                        Toast toastr = Toast.makeText(getContext(), "Running", Toast.LENGTH_SHORT);
                        toastr.show();

                        typ = 2;
                        hideNavigation();

                        break;
                    case 2: // Cycling
                        Toast toastc = Toast.makeText(getContext(), "Cycling", Toast.LENGTH_SHORT);
                        toastc.show();

                        typ = 3;
                        hideNavigation();

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Button btn_save = view.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_NAME, 0).edit();
                editor.putInt("Typ", typ);
                editor.apply();

                Toast toast = Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT);
                toast.show();


            }
        });

        return view;

    }

    public void hideNavigation(){
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        View decorView = getActivity().getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

    }
}
