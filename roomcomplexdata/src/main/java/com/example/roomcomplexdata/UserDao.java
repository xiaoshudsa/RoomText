package com.example.roomcomplexdata;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertStudent(User... Student);


    @Delete
    void deleteStudent(User... Student);


    @Update
    void UpdateStudent(User... Student);


    @Query("SELECT * FROM  User")
    List<User> getAllStudent();


    @Query("SELECT * FROM User WHERE id=:id")
    List<User> getAStudent(int id);

}
