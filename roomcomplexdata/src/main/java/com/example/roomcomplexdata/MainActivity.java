package com.example.roomcomplexdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserDao mUserDao;
    private StudentDao mStudentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyDataBase myDataBase = MyDataBase.getMyDataBase(this);
        mUserDao = myDataBase.getUserDao();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInsert();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserQuery();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInsert();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuery();
            }
        });
        mStudentDao = myDataBase.getStudentDao();
    }
    public void mUserInsert() {
          List<Department>  lists=new ArrayList<>();
          lists.add(new Department(1,"xiaohong"));
          lists.add(new Department(2,"xiaogang"));
        new InsertUserTask(mUserDao).execute(new User("xiaohua",21,"",lists));
    }

    class InsertUserTask extends AsyncTask<User,Void,Void> {
        private UserDao mStudentDao;

        public InsertUserTask(UserDao pStudentDao) {
            mStudentDao = pStudentDao;
        }

        @Override
        protected Void doInBackground(User... pStudents) {

            mStudentDao.insertStudent(pStudents);
            return null;
        }
    }


    public void mUserQuery() {
        new GetAllUserTask(mUserDao).execute();
    }
    class GetAllUserTask  extends AsyncTask<Void,Void,Void>{
        private UserDao mStudentDao;

        public GetAllUserTask(UserDao pStudentDao) {
            mStudentDao = pStudentDao;
        }

        @Override
        protected Void doInBackground(Void... pStudents) {

            List<User> allStudent = mStudentDao.getAllStudent();
            if (allStudent!=null&&allStudent.size()>0){
                for (User user : allStudent) {
                    Log.i("查询",user.toString());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void pVoid) {
            super.onPostExecute(pVoid);

        }
    }




    public void mInsert() {
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






    public void mQuery() {
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
            if (allStudent!=null&&allStudent.size()>0){
                for (Student user : allStudent) {
                    Log.i("查询",user.toString());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void pVoid) {
            super.onPostExecute(pVoid);

        }
    }









}
