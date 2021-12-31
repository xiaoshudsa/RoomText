package com.example.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private final StudentRepository mStudentRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        mStudentRepository = new StudentRepository(application);

    }


    public void InsertStudent(Student... pStudents) {
        mStudentRepository.InsertStudent(pStudents);

    }



    public void DeleterStudent(int  id) {
          mStudentRepository.DeleterStudent(id);
    }

    public void DeleterAllStudent() {
        mStudentRepository.DeleterAllStudent();
    }

    public void UpdateStudentTask(Student pStudents) {

        mStudentRepository.UpdateStudentTask(new Student(pStudents.id,pStudents.name,pStudents.age));
    }

    public LiveData<List<Student>> selectStudent() {
        return mStudentRepository.selectStudent();
    }

}
