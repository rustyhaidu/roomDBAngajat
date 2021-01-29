package com.example.roomdbangajat.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "angajat")
public class Angajat implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nume;
    private String functie;
    private String prenume;
    private String dataNasterii;
    private int salariu;
    private String sex;

    public Angajat() {
    }

    @Ignore
    public Angajat(int id, String nume,  String prenume, String functie, String dataNasterii, int salariu, String sex) {
        this.id = id;
        this.nume = nume;
        this.functie = functie;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.salariu = salariu;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(String dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                ", functie='" + functie + '\'' +
                ", prenume='" + prenume + '\'' +
                ", dataNasterii='" + dataNasterii + '\'' +
                ", salariu=" + salariu +
                ", sex='" + sex + '\'' +
                '}';
    }
}
