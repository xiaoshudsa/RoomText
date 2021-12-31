package com.example.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student... Student);


    @Delete
    void deleteStudent(Student... Student);

    @Query("DELETE  FROM student")
    void deleteAllStudent();

    @Update
    void UpdateStudent(Student... Student);


    @Query("SELECT * FROM  student")
    LiveData<List<Student>> getAllStudent();


    @Query("SELECT * FROM student WHERE id=:id")
    List<Student> getAStudent(int id);

}
