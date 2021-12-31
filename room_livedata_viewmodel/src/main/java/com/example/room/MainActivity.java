package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StudentDao mStudentDao;
    private StudentRecyclerViewAdapter mStudentRecyclerViewAdapter;
    private MyViewModel mViewModel;
    private int p=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycleView = findViewById(R.id.recycleView);
        ArrayList<Student> students = new ArrayList<>();

        mStudentRecyclerViewAdapter = new StudentRecyclerViewAdapter(students);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(mStudentRecyclerViewAdapter);

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewModel.class);
        mViewModel.selectStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> pStudents) {
                mStudentRecyclerViewAdapter.setStudents(pStudents);
                mStudentRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

    }

    public void mInsert(View view) {
        Student[] m={new Student("Jack",20),new Student("xiaohua",20)};
        mViewModel.InsertStudent(m);
    }

    public void mDelete(View view) {
       // new DeleterStudentTask(mStudentDao).execute(new Student(2));
        mViewModel.DeleterStudent(p++);
    }

    public void mUpdate(View view) {
        mViewModel.UpdateStudentTask(new Student(4,"xiaogang",21));
    }

    public void mQuery(View view) {
        mViewModel.DeleterAllStudent();

    }
}
