package com.example.roomtext;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StudentDao mStudentDao;
    private StudentRecyclerViewAdapter mStudentRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycleView = findViewById(R.id.recycleView);
        ArrayList<Student> students = new ArrayList<>();

        mStudentRecyclerViewAdapter = new StudentRecyclerViewAdapter(students);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(mStudentRecyclerViewAdapter);

        MyDataBase myDataBase = MyDataBase.getMyDataBase(this);
        mStudentDao = myDataBase.getStudentDao();

    }

    public void mInsert(View view) {
        Student[] m={new Student("Jack",20),new Student("xiaohua",20)};
        new InsertStudentTask(mStudentDao).execute(m);
    }

    class InsertStudentTask extends AsyncTask<Student,Void,Void>{
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


    public void mDelete(View view) {
        new DeleterStudentTask(mStudentDao).execute(new Student(2));
    }

    public void mUpdate(View view) {
        new UpdateStudentTask(mStudentDao).execute(new Student(4,"xiaogang",21));

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


    public void mQuery(View view) {
        new GetAllStudentTask(mStudentDao).execute();
    }
    class GetAllStudentTask  extends AsyncTask<Void,Void,Void>{
        private StudentDao mStudentDao;

        public GetAllStudentTask(StudentDao pStudentDao) {
            mStudentDao = pStudentDao;
        }

        @Override
        protected Void doInBackground(Void... pStudents) {

            List<Student> allStudent = mStudentDao.getAllStudent();
            mStudentRecyclerViewAdapter.setStudents(allStudent);
            return null;
        }

        @Override
        protected void onPostExecute(Void pVoid) {
            super.onPostExecute(pVoid);
            mStudentRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}
