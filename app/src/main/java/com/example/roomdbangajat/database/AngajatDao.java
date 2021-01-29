package com.example.roomdbangajat.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdbangajat.model.Angajat;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AngajatDao {

    // insert query
    @Insert(onConflict = REPLACE)
    void insert(Angajat angajat);

    // delete
    @Delete
    void delete(Angajat angajat);

    @Delete
    void reset(List<Angajat> angajatList);

    @Update
    void update(Angajat angajat);

    @Query("Select * from angajat")
    public List<Angajat> getAngajatList();

    @Query("Select * from angajat where id =:id")
    Angajat getGoalById(int id);
}
