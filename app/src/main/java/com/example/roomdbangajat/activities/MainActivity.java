package com.example.roomdbangajat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.roomdbangajat.R;
import com.example.roomdbangajat.database.AngajatDao;
import com.example.roomdbangajat.database.RoomDB;
import com.example.roomdbangajat.model.Angajat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText etIdAngajat;
    private EditText etNume;
    private EditText etPrenume;
    private EditText etFunctie;
    private RadioGroup radioGroup;
    private SeekBar seekBar;
    private EditText etDataNasterii;
    private DatePickerDialog picker;
    private Button saveBut;
    private Button vizualizareDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIdAngajat = findViewById(R.id.idAngajat);
        etNume = findViewById(R.id.editNume);
        etPrenume = findViewById(R.id.editPrenume);
        etFunctie = findViewById(R.id.editFunctie);

        radioGroup = findViewById(R.id.sex);
        final String[] sex = {"masculin"};
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.masculin) {
                    Toast.makeText(getApplicationContext(), "Masculin", Toast.LENGTH_SHORT).show();
                    sex[0] = "masculin";
                } else if (checkedId == R.id.feminin) {
                    sex[0] = "feminin";
                    Toast.makeText(getApplicationContext(), "Feminin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final int[] salariu = {0};
        seekBar = findViewById(R.id.baraSalariu);
        seekBar.setMax(100000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                salariu[0] = progress;
                Toast.makeText(getApplicationContext(), "seekbar progress: " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });


        etDataNasterii = findViewById(R.id.dataVarsta);
        etDataNasterii.setInputType(InputType.TYPE_NULL);
        etDataNasterii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etDataNasterii.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


        saveBut = findViewById(R.id.saveButton);
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Angajat angajat = null;
                RoomDB database = RoomDB.getInstance(getApplicationContext());
                AngajatDao angajatDao = database.angajatDao();

                if (etIdAngajat.getText().toString().isEmpty()) {
                    angajat = new Angajat();
                } else {
                    angajat = angajatDao.getGoalById(Integer.parseInt(etIdAngajat.getText().toString()));
                }

                angajat.setNume(etNume.getText().toString());
                angajat.setPrenume(etPrenume.getText().toString());
                angajat.setFunctie(etFunctie.getText().toString());
                angajat.setSex(sex[0]);
                angajat.setSalariu(salariu[0]);
                angajat.setDataNasterii(etDataNasterii.getText().toString());

                if (etIdAngajat.getText().toString().isEmpty()) {
                    angajatDao.insert(angajat);
                } else {
                    angajatDao.update(angajat);
                }

            }
        });

        vizualizareDate = findViewById(R.id.viewData);
        vizualizareDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AngajatListActivity.class);
                startActivity(intent);
            }
        });

        if (getIntent().getExtras() != null) {
            Angajat angajat = (Angajat) getIntent().getSerializableExtra("angajat");

            etIdAngajat.setText(String.valueOf(angajat.getId()));
            etNume.setText(angajat.getNume());
            etPrenume.setText(angajat.getPrenume());
            etFunctie.setText(angajat.getFunctie());
            etDataNasterii.setText(angajat.getDataNasterii());

            if (angajat.getSex().equals("masculin")) {
                radioGroup.check(R.id.masculin);
            } else if (angajat.getSex().equals("feminin")) {
                radioGroup.check(R.id.feminin);
            }

            seekBar.setProgress(angajat.getSalariu());
        }

    }
}
