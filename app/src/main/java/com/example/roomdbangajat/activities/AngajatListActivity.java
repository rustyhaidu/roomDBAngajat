package com.example.roomdbangajat.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomdbangajat.R;
import com.example.roomdbangajat.adaptors.AdaptorAngajat;
import com.example.roomdbangajat.database.AngajatDao;
import com.example.roomdbangajat.database.RoomDB;
import com.example.roomdbangajat.model.Angajat;

import java.util.List;


public class AngajatListActivity extends AppCompatActivity {
    private ListView listView;
    private AdaptorAngajat adaptorAngajat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angajat_list);

        RoomDB database = RoomDB.getInstance(getApplicationContext());
        AngajatDao angajatDao = database.angajatDao();

        List<Angajat> angajatList = angajatDao.getAngajatList();
        listView = findViewById(R.id.listViewAngajat);
        adaptorAngajat = new AdaptorAngajat(getApplicationContext(), R.layout.item_angajat, angajatList);
        listView.setAdapter(adaptorAngajat);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                TextView id = view.findViewById(R.id.itemId);
                TextView nume = view.findViewById(R.id.itemNume);
                TextView prenume = view.findViewById(R.id.itemprenume);
                TextView functie = view.findViewById(R.id.itemFunctie);
                TextView dataNasterii = view.findViewById(R.id.itemDataNasterii);
                TextView salariu = view.findViewById(R.id.itemSalariu);
                TextView sex = view.findViewById(R.id.itemSex);

                int idText = Integer.parseInt(id.getText().toString());
                String numeText = nume.getText().toString();
                String prenumeText = prenume.getText().toString();
                String functieText = functie.getText().toString();
                String datanasteriiText = dataNasterii.getText().toString();
                String sexText = sex.getText().toString();
                int salariuInt = Integer.parseInt(salariu.getText().toString());

                Angajat angajat = new Angajat(idText, numeText, prenumeText, functieText, datanasteriiText, salariuInt, sexText);
                intent.putExtra("angajat", angajat);
                startActivity(intent);
            }
        });

    }
}