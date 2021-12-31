package com.example.room;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

public class StudentRepository {
        private   StudentDao mStudentDao;
    public StudentRepository(Context pContext) {
            if (mStudentDao==null){
                MyDataBase myDataBase = MyDataBase.getMyDataBase(pContext);
                mStudentDao= myDataBase.getStudentDao();
            }
    }



    public void InsertStudent(Student... pStudents) {

        new InsertStudentTask(mStudentDao).execute(pStudents);
    }


    class InsertStudentTask extends AsyncTask<Student,Void,Void> {
        private StudentDao mStudentDao;

        public InsertStudentTask(StudentDao pStudentDao) {
            mStudentDao = pStudentDao;
        }

        @Override
        protected Void doInBackground(Student... pStudents) {

            mStudentDao.insertStudent(pStudents);
            return null;
        }
    }

    public void DeleterStudent(int  id) {
        new DeleterStudentTask(mStudentDao).execute(new Student(id));
    }

    public void DeleterAllStudent() {
        new DeleterAllStudentTask(mStudentDao).execute();
    }

    public void UpdateStudentTask(Student pStudents) {

        new UpdateStudentTask(mStudentDao).execute(new Student(pStudents.id,pStudents.name,pStudents.age));
    }

    public LiveData<List<Student>> selectStudent(Student... pStudents) {
      return mStudentDao.getAllStudent();
    }



    class DeleterStudentTask  extends AsyncTask<Student,Void,Void>{
        private StudentDao mStudentDao;

        public DeleterStudentTask(StudentDao pStudentDao) {
            mStudentDao = pStudentDao;
        }

        @Override
        protected Void doInBackground(Student... pStudents) {

            mStudentDao.deleteStudent(pStudents);
            return null;
        }
    }

    class DeleterAllStudentTask  extends AsyncTask<Student,Void,Void>{
        private StudentDao mStudentDao;

        public DeleterAllStudentTask(StudentDao pStudentDao) {
            mStudentDao = pStudentDao;
        }

        @Override
        protected Void doInBackground(Student... pStudents) {
            mStudentDao.deleteAllStudent();
            return null;
        }
    }


    class UpdateStudentTask  extends AsyncTask<Student,Void,Void>{
        private StudentDao mStudentDao;

        public UpdateStudentTask(StudentDao pStudentDao) {
            mStudentDao = pStudentDao;
        }

        @Override
        protected Void doInBackground(Student... pStudents) {

            mStudentDao.UpdateStudent(pStudents);
            return null;
        }
    }
}
